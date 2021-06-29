package com.fork.forkrpcall.rpc.protocol.frpc.handler;

import com.fork.forkrpcall.common.serialize.Serialization;
import com.fork.forkrpcall.remoting.FRpcChannel;
import com.fork.forkrpcall.remoting.Handler;
import com.fork.forkrpcall.rpc.Invoker;
import com.fork.forkrpcall.rpc.RpcInvocation;
import com.fork.forkrpcall.rpc.RpcResponse;
import redis.clients.jedis.Response;

/**
 *
 * */
public class FrpcServerHandler implements Handler {

    //协议中的handler 上一步是解码器解码，到这来的message其实就是一个 rpcInvocation对象了
    @Override
    public void onReceive(FRpcChannel fRpcChannel, Object message) throws Exception {
        RpcInvocation rpcInvocation = (RpcInvocation) message;
        System.out.println("收到 rpcInvocation 信息：" + rpcInvocation);
        //TODO 发起调用

        //发出数据 -- response
        RpcResponse response = new RpcResponse();

        try {
            Object result = getInvoker().invoke(rpcInvocation);
            response.setRequestId(rpcInvocation.getId());
            response.setStatus(200);
            response.setContent(result);
            System.out.println("服务端执行结果：" + result);
        }catch (Throwable e){
            response.setStatus(99);
            response.setContent(e.getMessage());
            e.printStackTrace();
        }

        //发送数据
        byte[] responseBody = getSerialization().serialize(response);
        fRpcChannel.send(responseBody);
    }

    @Override
    public void onWrite(FRpcChannel trpcChannel, Object message) throws Exception {

    }

    Invoker invoker;

    public void setInvoker(Invoker invoker) {
        this.invoker = invoker;
    }

    public Invoker getInvoker() {
        return this.invoker;
    }

    Serialization serialization;

    public void setSerialization(Serialization serialization) {
        this.serialization = serialization;
    }

    public Serialization getSerialization() {
        return this.serialization;
    }
}
