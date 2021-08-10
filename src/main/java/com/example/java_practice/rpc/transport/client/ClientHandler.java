package com.example.java_practice.rpc.transport.client;


import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            String value = (String) msg;
            //todo

        } finally {
            ReferenceCountUtil.release(msg);//释放对象引用计数
        }
    }
}
