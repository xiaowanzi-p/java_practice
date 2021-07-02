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
import java.util.Iterator;
import java.util.Set;

@Service
@Slf4j
public class NioServer {

    //服务端选择器
    private Selector serverSelector;
    //客户端选择器
    private Selector clientSelector;
    //客户端连接处理函数
    @Autowired
    private ClientHandler handler;

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
            //启动客户端连接处理程序
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

    //注册客户端通道
    private void registerClient(SocketChannel clientChannel) throws IOException {
        //客户端通道也设置非阻塞
        clientChannel.configureBlocking(false);
        clientChannel.register(clientSelector,SelectionKey.OP_READ);
    }

    //处理客户端通道连接
    private void startHandlerClient() {
        ThreadPoolUtils.execute(() -> {
            while (true) {
                //阻塞等待客户端连接可读
                try {
                    clientSelector.select();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Set<SelectionKey> selectionKeys = clientSelector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                        //多线程分发处理
                        ThreadPoolUtils.execute(() -> {
                            handler.apply(socketChannel);
                        });
                    }
                    //移除Set集合中的key
                    iterator.remove();
                }
            }
        });
    }
}
