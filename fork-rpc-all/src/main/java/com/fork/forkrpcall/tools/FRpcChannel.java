package com.fork.forkrpcall.tools;

/**
 * 此对象代表一个客户端连接
 *
 * */
public interface FRpcChannel {

    void send(byte[] message);
}
