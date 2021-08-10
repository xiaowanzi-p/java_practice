package com.example.java_practice.rpc.transport.client;

import com.example.java_practice.rpc.provider.ServerUrl;
import com.example.java_practice.rpc.stub.model.StubRequest;
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
import java.util.Map;

@Slf4j
public class TransportClient {


    private Map<ServerUrl,Channel> map = new HashMap<>(16);

    public void sendMessage(StubRequest request) {
        ServerUrl server = request.getServerUrl();
        //获取客户端连接
        Channel channel = createClient(server);


    }


    private synchronized Channel createClient(ServerUrl server) {
        //先从缓存中获取
        Channel channel = map.get(server);
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
                    ch.pipeline().addLast(new ClientHandler());
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync(); // (5)
            Channel ch = f.channel();
            map.put(server,ch);
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
