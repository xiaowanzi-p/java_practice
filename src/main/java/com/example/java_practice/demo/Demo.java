package com.example.java_practice.demo;

import com.example.java_practice.datastructure.graph.Graph;
import com.example.java_practice.netty.NettyServer;
import com.example.java_practice.nio.NormalNio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;

public class Demo {

    public static void main(String[] a) throws IOException {
        NettyServer.serverStart(7629);
    }
}


