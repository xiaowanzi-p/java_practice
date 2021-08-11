package com.example.java_practice.rpc.transport.server;

import com.alibaba.fastjson.JSONObject;
import com.example.java_practice.rpc.nameservice.ServerUrl;
import com.example.java_practice.rpc.stub.model.StubRequest;
import com.example.java_practice.rpc.stub.model.StubResponse;
import com.example.java_practice.utils.SpringHelper;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            String value = (String) msg;
            StubRequest stubRequest = JSONObject.parseObject(value, StubRequest.class);
            ServerUrl server = stubRequest.getServerUrl();
            String path = server.getPath();
            String[] split = path.split("#");
            String className = split[0];
            String methodName = split[1];
            Object request = stubRequest.getRequest();
            //执行目标方法
            Object result = executeTargetMethod(className, methodName, request);
            //将结果返回
            StubResponse stubResponse = StubResponse.build(stubRequest.getRequestId(), stubRequest.getVersion(), result);
            ctx.writeAndFlush(JSONObject.toJSONString(stubResponse));
        } finally {
            ReferenceCountUtil.release(msg);//释放对象引用计数
        }
    }


    private Object executeTargetMethod(String className, String methodName, Object request) throws InvocationTargetException, IllegalAccessException {
        Class<?> clazz = SpringHelper.getBeanClass(className);
        Object bean = SpringHelper.getBean(className);
        Method[] methods = clazz.getMethods();
        Method target = null;

        for (Method method : methods) {
            String name = method.getName();
            if (name.contentEquals(methodName)) {
                target = method;
                break;
            }
        }

        Object result = target.invoke(bean, request);
        return result;
    }
}
