package com.fork.forkrpcall.remoting;

import java.util.List;

/**
 * 留下这个借口 以后遇到其他协议也要实现这个借口。
 * */
public interface Codec {

    byte[] encode(Object msg) throws Exception;

    /**
     * 解码得拆包吧 所以就得是list
     * */
    List<Object> decode(byte[] message) throws Exception;

    /**
     * 创建实例
     * */
    Codec createInstance();

}
