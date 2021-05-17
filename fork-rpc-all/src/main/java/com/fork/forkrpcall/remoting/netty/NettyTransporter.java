package com.fork.forkrpcall.remoting.netty;

import com.fork.forkrpcall.remoting.Codec;
import com.fork.forkrpcall.remoting.Handler;
import com.fork.forkrpcall.remoting.Server;
import com.fork.forkrpcall.remoting.Transporter;

import java.net.URI;

public class NettyTransporter implements Transporter {
    @Override
    public Server start(URI uri, Codec codec, Handler handler) {
        NettyServer nettyServer = new NettyServer();
        nettyServer.start(uri,codec,handler);
        return nettyServer;
    }
}
