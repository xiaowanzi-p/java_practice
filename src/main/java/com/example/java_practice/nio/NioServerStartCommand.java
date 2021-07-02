package com.example.java_practice.nio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class NioServerStartCommand implements CommandLineRunner {

    @Autowired
    private NioServer nioServer;

    @Override
    public void run(String... args) throws Exception {
        nioServer.serverStart(7629);
    }
}
