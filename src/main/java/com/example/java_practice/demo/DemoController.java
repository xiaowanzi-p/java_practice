package com.example.java_practice.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @PostMapping("/test")
    public void test() {
        try {
            InetSocketAddress address = new InetSocketAddress("localhost", 7629);
            SocketChannel open = SocketChannel.open();
            open.connect(address);
            ByteBuffer wrap = ByteBuffer.wrap("你好测试".getBytes());
            open.write(wrap);
        } catch (Exception e) {

        }
    }
}

