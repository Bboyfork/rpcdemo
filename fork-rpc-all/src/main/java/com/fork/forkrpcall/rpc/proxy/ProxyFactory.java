package com.fork.forkrpcall.rpc.proxy;

import com.fork.forkrpcall.rpc.Invoker;
import com.fork.forkrpcall.rpc.RpcInvocation;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Proxy工厂 返回一个invoke接口的实现
 * */
public class ProxyFactory {
    public static Object getProxy(Invoker invoker,Class<?>[] interfaces){
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),interfaces,new InvokerInvocationHandler(invoker));
    }

    public static Invoker getInvoker(Object proxy,Class type){
        return new Invoker() {
            @Override
            public Class getInterface() {
                return type;
            }

            @Override
            public Object invoke(RpcInvocation rpcInvocation) throws Exception{
                //反射调用
                Method method = proxy.getClass().getMethod(rpcInvocation.getMethodName(), rpcInvocation.getParameterTypes());
                return method.invoke(proxy,rpcInvocation.getArguments());
            }
        };
    }

}
