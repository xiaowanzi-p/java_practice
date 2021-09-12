package com.example.java_practice.rpc.transport.client;


import com.alibaba.fastjson.JSONObject;
import com.example.java_practice.rpc.nameservice.ServerUrl;
import com.example.java_practice.rpc.stub.model.StubResponse;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class ClientHandler extends ChannelHandlerAdapter {

    private Map<String, CompletableFuture<StubResponse>> requestMap;
    private Map<ServerUrl, Channel> serverMap;
    private Map<Channel,ServerUrl> revertServerMap;

    public ClientHandler() {
    }

    public ClientHandler(Map<String, CompletableFuture<StubResponse>> requestMap, Map<Channel,ServerUrl> revertServerMap, Map<ServerUrl, Channel> serverMap) {
        this.requestMap = requestMap;
        this.revertServerMap = revertServerMap;
        this.serverMap = serverMap;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            String value = (String) msg;
            StubResponse response = JSONObject.parseObject(value, StubResponse.class);
            String requestId = response.getRequestId();
            requestMap.get(requestId).complete(response);
        } finally {
            ReferenceCountUtil.release(msg);//释放对象引用计数
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //断开连接时移除客户端连接缓存
        Channel channel = ctx.channel();
        ServerUrl serverUrl = revertServerMap.get(channel);
        serverMap.remove(serverUrl);
    }
}
