package com.fork.forkrpcall.rpc;

/**
 * 消费者调用服务，通过Invoker对象
 * 服务提供者调用 具体实现类，也通过Invoker对象
 * */
public interface Invoker {

    Class getInterface();

    Object invoke(RpcInvocation rpcInvocation) throws Exception;

}
