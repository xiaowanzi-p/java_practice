package com.example.java_practice.rpc.nameservice;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class DefaultNameService implements NameService {

    private Map<String,ServerUrl> map = new HashMap<>(16);

    @Override
    public void register(ServerUrl serverUrl) {
        map.put(serverUrl.getName(),serverUrl);
    }

    @Override
    public ServerUrl getServer(String name) {
        return map.get(name);
    }

    @Override
    public void remove(String name) {
        map.remove(name);
    }
}
