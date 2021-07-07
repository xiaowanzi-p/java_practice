package com.example.java_practice.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class EncodeHandler extends MessageToByteEncoder {

    /**
     * 编码器,将返回客户端的数据编码成字节数组
     */
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        String value = (String) o;
        byte[] bytes = value.getBytes();
        byteBuf.writeBytes(bytes);
    }
}
