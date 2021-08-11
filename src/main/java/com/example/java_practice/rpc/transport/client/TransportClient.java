package com.example.java_practice.rpc.transport.client;

import com.alibaba.fastjson.JSONObject;
import com.example.java_practice.rpc.nameservice.ServerUrl;
import com.example.java_practice.rpc.stub.model.StubRequest;
import com.example.java_practice.rpc.stub.model.StubResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class TransportClient {

    //存储客户端连接
    private Map<ServerUrl,Channel> serverMap = new HashMap<>(16);
    //反转存储客户端连接
    private Map<Channel,ServerUrl> revertServerMap = new HashMap<>(16);
    //所有的在途请求
    private Map<String, CompletableFuture<StubResponse>> requestMap = new HashMap<>(16);

    public CompletableFuture<StubResponse> sendMessage(StubRequest request) {
        ServerUrl server = request.getServerUrl();
        CompletableFuture<StubResponse> future = new CompletableFuture<>();
        try {
            //获取客户端连接
            Channel channel = createClient(server);
            //发送请求
            ChannelFuture channelFuture = channel.writeAndFlush(JSONObject.toJSONString(request));
            channelFuture.addListener((futureListener) -> {

            })
            //存储请求待返回可以回调填充结果
            requestMap.put(request.getRequestId(),future);
        } catch (Exception e) {
            log.error("TransportClient sendMessage error",e);
            future.completeExceptionally(e);
        }
        return future;
    }


    private synchronized Channel createClient(ServerUrl server) {
        //先从缓存中获取
        Channel channel = serverMap.get(server);
        if (channel != null) {
            return channel;
        }

        String host = server.getHost();
        Integer port = server.getPort();

        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            b.channel(NioSocketChannel.class); // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new StringDecoder());
                    ch.pipeline().addLast(new StringEncoder());
                    ch.pipeline().addLast(new ClientHandler(requestMap,revertServerMap,serverMap));
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync(); // (5)
            Channel ch = f.channel();
            serverMap.put(server,ch);
            return ch;
            // Wait until the connection is closed.
            //f.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("create TransportClient error",e);
        } finally {
            workerGroup.shutdownGracefully();
        }
        return null;
    }

}
