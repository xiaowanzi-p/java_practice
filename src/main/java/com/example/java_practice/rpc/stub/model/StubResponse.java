package com.example.java_practice.rpc.stub.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StubResponse {

    private String requestId;

    private int version;

    private int code;

    private String message;

    private Object response;

    public static StubResponse build(String requestId, int version, Object response) {
        StubResponse stub = new StubResponse();
        if (response != null) {
            stub.setResponse(response);
        }
        stub.setRequestId(requestId);
        stub.setVersion(version);
        return stub;
    }
}
