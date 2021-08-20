package com.example.java_practice.rpc.stub;

import com.example.java_practice.rpc.nameservice.ServerUrl;
import com.example.java_practice.rpc.transport.client.TransportClient;

public class Stub implements StubInter {

    private ServerUrl serverUrl;
    private TransportClient transportClient;

    public ServerUrl getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(ServerUrl serverUrl) {
        this.serverUrl = serverUrl;
    }

    public TransportClient getTransportClient() {
        return transportClient;
    }

    public void setTransportClient(TransportClient transportClient) {
        this.transportClient = transportClient;
    }

}
