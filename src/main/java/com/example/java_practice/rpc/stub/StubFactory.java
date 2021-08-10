package com.example.java_practice.rpc.stub;

public interface StubFactory {

    <T> T createStub(Class<T> clazz);
}
