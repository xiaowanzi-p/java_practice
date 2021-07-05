package com.example.java_practice.nio;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

@Component
@Slf4j
public class MyClientHandler extends AbstractClientHandler {

    @Override
    protected void interruptConnect(SocketChannel channel, SelectionKey selectionKey) {
        log.info("客户端断开了连接");
    }

    @Override
    protected void handlerRequest(SocketChannel channel, SelectionKey selectionKey, ByteBuffer messge) {
        try {
            byte[] array = messge.array();
            String info = new String(array, "UTF-8");
            String response = "我已经收到信息: " + info;
            ByteBuffer wrap = ByteBuffer.wrap(response.getBytes("UTF-8"));
            channel.write(wrap);
        } catch (Exception e) {

        }
    }
}
