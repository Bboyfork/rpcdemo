package com.fork.forkrpcall.remoting;

/**
 * 协议来实现
 * */
public interface Handler {

    void onReceive(TrpcChannel trpcChannel,Object message) throws Exception;

    void onWrite(TrpcChannel trpcChannel,Object message) throws Exception;
}
