package com.example.java_practice.rpc.stub.model;

import com.example.java_practice.rpc.provider.ServerUrl;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StubRequest {

    private String requestId;

    private int version;

    private ServerUrl serverUrl;

    private Object request;
}
