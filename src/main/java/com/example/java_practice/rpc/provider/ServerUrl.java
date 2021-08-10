package com.example.java_practice.rpc.provider;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;


@Getter
@Setter
public class ServerUrl {
    //服务名称,唯一的标识
    private String name;
    //主机
    private String host;
    //端口
    private Integer port;
    //服务路径
    private String path;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServerUrl serverUrl = (ServerUrl) o;
        return Objects.equals(host, serverUrl.host) && Objects.equals(port, serverUrl.port);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, port);
    }
}
