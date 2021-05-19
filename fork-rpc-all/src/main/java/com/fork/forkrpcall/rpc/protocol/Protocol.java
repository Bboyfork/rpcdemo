package com.fork.forkrpcall.rpc.protocol;

import com.fork.forkrpcall.rpc.Invoker;

import java.net.URI;

public interface Protocol {

    /**
     * 对外开放服务
     * @param exportUri 协议名称://IP:端口/service全类名?参数名=参数值
     * @param invoker 调用具体实现类的代理对象
     * */
    public void export(URI exportUri, Invoker invoker);

    /**
     *
     * */
    public Invoker refer(URI consumerUri);
}
