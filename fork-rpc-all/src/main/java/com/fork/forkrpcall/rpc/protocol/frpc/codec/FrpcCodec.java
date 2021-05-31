package com.fork.forkrpcall.rpc.protocol.frpc.codec;

import com.fork.forkrpcall.remoting.Codec;

import java.util.List;

/**
 *
 * */
public class FrpcCodec implements Codec {
    @Override
    public byte[] encode(Object msg) throws Exception {
        return new byte[0];
    }

    @Override
    public List<Object> decode(byte[] message) throws Exception {
        return null;
    }

    @Override
    public Codec createInstance() {
        return null;
    }
}
