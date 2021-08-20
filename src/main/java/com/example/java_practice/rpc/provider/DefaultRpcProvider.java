package com.example.java_practice.rpc.provider;

import com.example.java_practice.rpc.nameservice.NameService;
import com.example.java_practice.rpc.nameservice.ServerUrl;
import com.example.java_practice.rpc.stub.StubFactory;
import lombok.Setter;

@Setter
public class DefaultRpcProvider implements RpcProvider{

    private NameService nameService;
    private StubFactory stubFactory;

    @Override
    public <T> T getRemoteService(Class<T> clazz) {
        //从NameService注册中心获取服务地址
        String name = clazz.getName();
        ServerUrl server = nameService.getServer(name);

        //动态生成代理Stub
        return null;
    }

    @Override
    public <T> ServerUrl registerService(T t) {
        return null;
    }

    @Override
    public void startService() {

    }
}
