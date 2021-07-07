package com.example.java_practice.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class DecodeHandler extends ByteToMessageDecoder {

    /**
     * 解决TCP粘包问题-采用自说明完整数据包长度
     */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.isReadable()) {
            //数据包第一个字节说明完整包长度
            byte[] array = new byte[1];
            int i = byteBuf.readerIndex();
            byteBuf.getBytes(i,array);
            //完整数据包的长度
            Integer length = Integer.valueOf(new String(array));

            //接收的数据长度
            int dataLength = byteBuf.readableBytes()-1;
            //只有接收数据长度大于等于完整包长度时，说明接收到了一个完整的数据包
            if (dataLength >= length) {
                //读取完整的数据包到新的ByteBuff中,并解码成String对象
                byteBuf.readByte();
                ByteBuf message = byteBuf.readBytes(length);
                String value = new String(message.array(), "UTF-8");
                //将解析的完整数据包放入处理集合中
                list.add(value);
            }
        }
    }
}
