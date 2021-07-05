package com.example.java_practice.demo;

import com.example.java_practice.nio.NioServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private NioServer nioServer;

    @PostMapping("/test")
    public void test() {
        //nioServer.startHandlerClient();
    }
}

