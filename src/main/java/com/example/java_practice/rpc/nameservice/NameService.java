package com.example.java_practice.rpc.nameservice;

public interface NameService {

    void register(ServerUrl serverUrl);

    ServerUrl getServer(String name);

    void remove(String name);
}
