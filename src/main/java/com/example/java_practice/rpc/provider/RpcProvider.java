package com.example.java_practice.rpc.provider;

import com.example.java_practice.rpc.nameservice.ServerUrl;

public interface RpcProvider {

    /**
     * 客户端获取远程服务的代理实现Stub
     *
     * @param clazz
     * @param <T>
     */
   <T> T getRemoteService(Class<T> clazz);


    /**
     * 服务端注册服务可以给客户端使用
     *
     * @param t
     * @param <T>
     * @return
     */
   <T> ServerUrl registerService(T t);

    /**
     * 启动RPC服务
     */
   void startService();
}
