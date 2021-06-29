package com.fork.forkrpcall.protocol;


import com.fork.forkrpcall.common.serialize.json.JsonSerialization;
import com.fork.forkrpcall.remoting.netty.NettyTransporter;
import com.fork.forkrpcall.rpc.RpcInvocation;
import com.fork.forkrpcall.rpc.protocol.frpc.codec.FrpcCodec;
import com.fork.forkrpcall.rpc.protocol.frpc.handler.FrpcServerHandler;

import java.net.URI;
import java.net.URISyntaxException;

public class FrpcProtocolTransporterTest {
    public static void main(String[] args) throws URISyntaxException {
        FrpcCodec frpcCodec = new FrpcCodec();
        frpcCodec.setDecodeType(RpcInvocation.class);
        frpcCodec.setSerialization(new JsonSerialization());

        FrpcServerHandler frpcServerHandler = new FrpcServerHandler();

        NettyTransporter nettyTransporter = new NettyTransporter();
        nettyTransporter.start(new URI("FRPP://127.0.0.1:8080"),frpcCodec,frpcServerHandler);
    }
}
