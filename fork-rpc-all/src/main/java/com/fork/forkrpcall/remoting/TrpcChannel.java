package com.fork.forkrpcall.remoting;

/**
 * 此对象代表一个客户端连接
 *
 * */
public interface TrpcChannel {

    void send(byte[] message);
}
