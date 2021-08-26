package com.example.java_practice.rpc.stub.model;

import com.example.java_practice.rpc.nameservice.ServerUrl;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class StubRequest {

    private String requestId;

    private int version;

    private ServerUrl serverUrl;

    private Object[] request;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StubRequest that = (StubRequest) o;
        return Objects.equals(requestId, that.requestId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId);
    }
}
