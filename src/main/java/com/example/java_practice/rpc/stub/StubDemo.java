package com.example.java_practice.rpc.stub;

import com.example.java_practice.rpc.nameservice.ServerUrl;
import com.example.java_practice.rpc.transport.client.TransportClient;

import java.util.function.UnaryOperator;

public class StubDemo implements Stub {

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


    /*@Override
    public Object test(Object[] param) {
        Object o = sendRemoteMessage(param, "");
        return o;
    }*/

   /* private Object sendRemoteMessage(Object param, String methodName) {
        String url = serverUrl.getName() + "#" + methodName;
        serverUrl.setPath(url);
        StubRequest request = getRequest(param);
        CompletableFuture<StubResponse> future = transportClient.sendMessage(request);
        try {
            StubResponse response = future.get(5, TimeUnit.SECONDS);
            return response.getResponse();
        } catch (Exception e) {
            throw new RuntimeException("RPC请求远程服务超时");
        }
    }*/

   /* private StubRequest getRequest(Object param) {
        StubRequest request = new StubRequest();
        request.setServerUrl(serverUrl);
        request.setRequest(param);
        request.setRequestId(UUID.randomUUID().toString());
        request.setVersion(1);
        return request;
    }*/
}
