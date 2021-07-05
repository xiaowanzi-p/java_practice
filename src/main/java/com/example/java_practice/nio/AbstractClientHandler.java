package com.example.java_practice.nio;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

@Slf4j
public abstract class AbstractClientHandler {

    public void handler(SocketChannel channel, SelectionKey selectionKey) {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            boolean isClose = false;
            while (true) {
                int read = channel.read(buffer);
                //客户端关闭连接
                if (read == -1) {
                    channel.close();
                    selectionKey.cancel();
                    isClose = true;
                    //断开连接回调函数
                    interruptConnect(channel, selectionKey);
                    break;
                }
                if (read <= 0) {
                    break;
                }
            }
            if (isClose) {
                return;
            }

            //具体处理请求函数
            handlerRequest(channel,selectionKey,buffer);
        } catch (Exception e) {
            log.error("客户端处理失败",e);
        }
    }

    //中断连接回调函数
    protected abstract void interruptConnect(SocketChannel channel, SelectionKey selectionKey);

    //具体处理请求函数
    protected abstract void handlerRequest(SocketChannel channel, SelectionKey selectionKey,ByteBuffer messge);
}
