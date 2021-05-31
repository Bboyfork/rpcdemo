package com.fork.forkrpcall.rpc.protocol.frpc;

import com.fork.forkrpcall.rpc.Invoker;
import com.fork.forkrpcall.rpc.RpcInvocation;

public class FrpcClientInvoker implements Invoker {
    @Override
    public Class getInterface() {
        return null;
    }

    @Override
    public Object invoke(RpcInvocation rpcInvocation) throws Exception {
        //序列化
        //发起请求
        //另一个线程 接收响应 解码——> handler
        //

        return null;
    }
}
