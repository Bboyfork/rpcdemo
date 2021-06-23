package com.fork.forkrpcall.tools.netty;

import com.fork.forkrpcall.tools.FRpcChannel;
import io.netty.channel.Channel;

public class NettyChannel implements FRpcChannel {

    Channel channel;
    public NettyChannel (Channel channel){this.channel = channel;}

    @Override
    public void send(byte[] message) {
        channel.writeAndFlush(message);
    }
}
