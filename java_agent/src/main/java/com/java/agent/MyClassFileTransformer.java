package com.java.agent;


import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import lombok.extern.slf4j.Slf4j;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

@Slf4j
public class MyClassFileTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        if (className.contains("TestUser")) {
            try {
                ClassPool classPool = ClassPool.getDefault();
                CtClass ctClass = classPool.getCtClass(className);
                CtMethod ctMethod = new CtMethod(CtClass.voidType, "demoMethod", new CtClass[]{}, ctClass);
                ctMethod.setBody("System.out.println(\"这是一个新增的字节码方法\");");
                ctClass.addMethod(ctMethod);
                return ctClass.toBytecode();
            } catch (Exception e) {
                log.error("修改字节码错误, Name:{}",className,e);
            }
        }

        return null;
    }
}
