package com.example.java_practice.nio;

import com.example.java_practice.utils.ThreadPoolUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class NioServer {

    //服务端选择器
    private Selector serverSelector;
    //客户端选择器
    private Selector clientSelector;
    //客户端连接处理函数
    @Autowired
    private AbstractClientHandler handler;

    public synchronized void serverStart(int port) {
        try {
            if (serverSelector != null) {
                throw new RuntimeException("不要重复启动服务端");
            }
            //创建一个服务端选择器和客户端选择器
            serverSelector = Selector.open();
            clientSelector = Selector.open();
            //创建一个服务端
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            //设置非阻塞通道
            serverChannel.configureBlocking(false);
            //服务端绑定接口
            ServerSocket serverSocket = serverChannel.socket();
            InetSocketAddress address = new InetSocketAddress(port);
            serverSocket.bind(address);
            //通道注册选择器，服务端只能监听ACCEPT事件
            serverChannel.register(serverSelector, SelectionKey.OP_ACCEPT);
            //启动客户端处理程序
            startHandlerClient();
            log.info("服务端启动成功...");
            //主线程循环获取连接的客户端
            while (true) {
                //阻塞等待客户端的连接
                serverSelector.select();
                //获取连接上的客户端
                Set<SelectionKey> keys = serverSelector.selectedKeys();
                //此处selector只注册了一个serverchannel所以直接获取
                Iterator<SelectionKey> iterator = keys.iterator();
                SelectionKey selectionKey = iterator.next();
                ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                //获取连接上的客户端
                SocketChannel clientChannel = server.accept();
                //将客户端通道注册到客户端选择器上
                registerClient(clientChannel);
                //移除服务端选择器的Set<SelectionKey>
                iterator.remove();
            }
        } catch (Exception e) {
            log.error("服务端启动失败",e);
        }
    }

    //注册客户端通道,多线程处理连接注册 Reactor模型
    private void registerClient(SocketChannel clientChannel) {
        ThreadPoolUtils.execute(() -> {
            try {
                //客户端通道也设置非阻塞
                log.info("收到一个客户端连接");
                clientChannel.configureBlocking(false);
                clientChannel.register(clientSelector,SelectionKey.OP_READ);
                //注册新连接需要唤醒之前的选择器,重新选择让新注册的连接生效
                clientSelector.wakeup();
            } catch (Exception e) {
                log.error("注册新连接失败",e);
            }
        });
    }

    //处理客户端通道连接
    private void startHandlerClient() {
        log.info("启动客户端处理程序...");
        ThreadPoolUtils.execute(() -> {
            while (true) {
                //阻塞等待客户端连接可读
                try {
                    int select = clientSelector.select();
                    if (select <= 0) {
                        continue;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //已经准备好可以读的客户端连接
                Set<SelectionKey> selectionKeys = clientSelector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                List<CompletableFuture> list = new ArrayList();

                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isReadable()) {
                        log.info("收到一个客户端的写入");
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        //多线程Reactor处理客户端连接
                        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> handler.handler(socketChannel, selectionKey), ThreadPoolUtils.getPoolExecutor());
                        if (future != null) {
                            list.add(future);
                        }
                    }
                }

                //等待这批准备好的客户端请求完毕在进行下一次选择
                try {
                    CompletableFuture.allOf(list.toArray(new CompletableFuture[0])).get();
                    //清除Set<SelectionKey>集合
                    selectionKeys.clear();
                } catch (Exception e) {

                }
            }
        });
    }
}
