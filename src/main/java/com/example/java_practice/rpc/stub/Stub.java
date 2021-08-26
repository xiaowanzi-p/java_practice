package com.example.java_practice.rpc.stub;

import com.example.java_practice.rpc.nameservice.ServerUrl;
import com.example.java_practice.rpc.transport.client.TransportClient;

public interface Stub {

    ServerUrl getServerUrl();

    void setServerUrl(ServerUrl serverUrl);

    TransportClient getTransportClient();

    void setTransportClient(TransportClient transportClient);
}
