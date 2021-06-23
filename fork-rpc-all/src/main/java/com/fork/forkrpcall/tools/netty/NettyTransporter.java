package com.fork.forkrpcall.tools.netty;

import com.fork.forkrpcall.tools.Codec;
import com.fork.forkrpcall.tools.Handler;
import com.fork.forkrpcall.tools.Server;
import com.fork.forkrpcall.tools.Transporter;

import java.net.URI;

public class NettyTransporter implements Transporter {
    @Override
    public Server start(URI uri, Codec codec, Handler handler) {
        NettyServer nettyServer = new NettyServer();
        nettyServer.start(uri,codec,handler);
        return nettyServer;
    }
}
