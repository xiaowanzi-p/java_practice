package com.example.java_practice.nio;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

@Component
public class ClientHandler {

    public void apply(SocketChannel channel) {
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true) {
                int read = channel.read(buffer);
                if (read == -1) {
                    break;
                }
            }
            byte[] array = buffer.array();
            String message = new String(array, "UTF-8");
            String response = "我已经收到信息: " + message;
            ByteBuffer wrap = ByteBuffer.wrap(response.getBytes());
            channel.write(wrap);
        } catch (Exception e) {

        }
    }
}
