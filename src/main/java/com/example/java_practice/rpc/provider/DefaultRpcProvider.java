package com.example.java_practice.rpc.provider;

public class DefaultRpcProvider implements RpcProvider{

    @Override
    public <T> T getRemoteService(Class<T> clazz) {
        //从NameService注册中心获取服务地址

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
