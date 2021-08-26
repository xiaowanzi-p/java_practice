package com.example.java_practice.rpc.stub;

import groovy.lang.GroovyClassLoader;

import java.lang.reflect.Method;

public class DefaultStubFactory implements StubFactory {

    @Override
    public <T> T createStub(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        String className = clazz.getSimpleName();
        String importString = "import " + clazz.getName() + ";";
        Method[] methods = clazz.getMethods();

        StringBuilder builder = new StringBuilder();
        for (Method method : methods) {
            String methodName = method.getName();
            builder.append(String.format(methodTemplate,methodName,method));
            builder.append("\n");
        }

        String methodString = builder.toString();
        String classString = String.format(template,importString,className,className,methodString,subTemplate);

        GroovyClassLoader groovyClassLoader = new GroovyClassLoader();
        Class<?> aClass = groovyClassLoader.parseClass(classString);
        Object o = aClass.newInstance();
        return (T) o;
    }


    private static String subTemplate = "private Object sendRemoteMessage(Object[] param, String methodName) {\n" +
            "        String url = serverUrl.getName() + \"#\" + methodName;\n" +
            "        serverUrl.setPath(url);\n" +
            "        StubRequest request = getRequest(param);\n" +
            "        CompletableFuture<StubResponse> future = transportClient.sendMessage(request);\n" +
            "        try {\n" +
            "            StubResponse response = future.get(5, TimeUnit.SECONDS);\n" +
            "            return response.getResponse();\n" +
            "        } catch (Exception e) {\n" +
            "            throw new RuntimeException(\"RPC请求远程服务超时\");\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "    private StubRequest getRequest(Object[] param) {\n" +
            "        StubRequest request = new StubRequest();\n" +
            "        request.setServerUrl(serverUrl);\n" +
            "        request.setRequest(param);\n" +
            "        request.setRequestId(UUID.randomUUID().toString());\n" +
            "        request.setVersion(1);\n" +
            "        return request;\n" +
            "    }";


    private static String template = "package com.example.java_practice.rpc.stub;\n" +
            "\n" +
            "import com.example.java_practice.rpc.nameservice.ServerUrl;\n" +
            "import com.example.java_practice.rpc.stub.model.StubRequest;\n" +
            "import com.example.java_practice.rpc.stub.model.StubResponse;\n" +
            "import com.example.java_practice.rpc.stub.Stub;\n" +
            "import com.example.java_practice.rpc.transport.client.TransportClient;\n" +
            "\n" +
            "import java.util.UUID;\n" +
            "import java.util.concurrent.CompletableFuture;\n" +
            "import java.util.concurrent.TimeUnit;\n" +
            "\n" +
            "%s" +
            "public class %sStub implements %s, Stub {\n" +
            "\n" +
            "    private ServerUrl serverUrl;\n" +
            "    private TransportClient transportClient;\n" +
            "\n" +
            "    public ServerUrl getServerUrl() {\n" +
            "        return serverUrl;\n" +
            "    }\n" +
            "\n" +
            "    public void setServerUrl(ServerUrl serverUrl) {\n" +
            "        this.serverUrl = serverUrl;\n" +
            "    }\n" +
            "\n" +
            "    public TransportClient getTransportClient() {\n" +
            "        return transportClient;\n" +
            "    }\n" +
            "\n" +
            "    public void setTransportClient(TransportClient transportClient) {\n" +
            "        this.transportClient = transportClient;\n" +
            "    }\n" +

            "%s" +
            "\n" +
            "%s" +

            "}";


    private static String methodTemplate = "@Override\n" +
            "    public Object %s(Object[] param) {\n" +
            "        Object o = sendRemoteMessage(param, %s);\n" +
            "        return o;\n" +
            "    }";
}
