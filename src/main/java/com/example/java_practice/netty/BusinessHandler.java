package com.example.java_practice.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class BusinessHandler extends ChannelHandlerAdapter {

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //handler生命周期方法
        log.info("hander增加了");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //handler生命周期方法
        log.info("hander移除了");
    }

    /**
     * 每当客户端socket读缓冲区有数据时则调用该方法
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            String value = (String) msg;
            //netty采用异步编程处理,写出数据也是异步。返回的是个Future,需要注册回调监听器来确定数据写出了
            ChannelFuture channelFuture = ctx.writeAndFlush(msg);
            channelFuture.addListener(future -> {
                log.info("请求已经写出了");
            });

        } finally {
            ReferenceCountUtil.release(msg);//释放对象引用计数
        }
    }

    /**
     * 任何请求处理过程出错就会调用该方法
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        String message = cause.getMessage();
        log.error("处理报错了: " + message);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        /*AttributeKey<Object> key = AttributeKey.valueOf("selfDisconnect");
        Attribute<Object> value = ctx.channel().attr(key);
        value.set("selfDisconnectValue");*/
        log.info("收到了一个客户端新连接");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        AttributeKey<Object> key = AttributeKey.valueOf("selfDisconnect");
        Attribute<Object> value = ctx.channel().attr(key);
        Object o = value.get();
        boolean b = o == null;
        System.out.println("获取的值为: " + b);
        log.info("断开了一个客户端连接");
    }

}
