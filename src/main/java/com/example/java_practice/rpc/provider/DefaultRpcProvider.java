package com.example.java_practice.rpc.provider;

import com.example.java_practice.rpc.nameservice.DefaultNameService;
import com.example.java_practice.rpc.nameservice.NameService;
import com.example.java_practice.rpc.nameservice.ServerUrl;
import com.example.java_practice.rpc.stub.DefaultStubFactory;
import com.example.java_practice.rpc.stub.Stub;
import com.example.java_practice.rpc.stub.StubFactory;
import com.example.java_practice.rpc.transport.client.TransportClient;
import lombok.Setter;

@Setter
public class DefaultRpcProvider implements RpcProvider{

    private NameService nameService;
    private StubFactory stubFactory;

    @Override
    public <T> T getRemoteService(Class<T> clazz) throws InstantiationException, IllegalAccessException {
        //从NameService注册中心获取服务地址
        String name = clazz.getName();
        ServerUrl server = nameService.getServer(name);
        //动态生成代理Stub
        T stub = stubFactory.createStub(clazz);
        Stub inter = (Stub) stub;
        inter.setServerUrl(server);
        TransportClient transportClient = new TransportClient();
        inter.setTransportClient(transportClient);
        return stub;
    }

    @Override
    public <T> ServerUrl registerService(T t) {
        ServerUrl serverUrl = new ServerUrl();
        Class<?> clazz = t.getClass();
        String className = clazz.getName();
        serverUrl.setName(className);
        serverUrl.setHost("localhost");
        serverUrl.setPort(7629);
        nameService.register(serverUrl);
        return serverUrl;
    }

    @Override
    public void startService() {
        //初始化NameService
        NameService nameService = new DefaultNameService();
        this.nameService = nameService;
        //初始化StubFactory
        DefaultStubFactory stubFactory = new DefaultStubFactory();
        this.stubFactory = stubFactory;



    }
}
