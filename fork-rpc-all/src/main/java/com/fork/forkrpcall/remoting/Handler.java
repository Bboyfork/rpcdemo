package com.fork.forkrpcall.remoting;

/**
 * 协议来实现
 * */
public interface Handler {

    void onReceive(FRpcChannel trpcChannel, Object message) throws Exception;

    void onWrite(FRpcChannel trpcChannel, Object message) throws Exception;
}
