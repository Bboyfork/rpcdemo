package com.fork.forkrpcall.rpc.protocol.frpc;

import com.fork.forkrpcall.common.serialize.Serialization;
import com.fork.forkrpcall.common.util.SpiUtils;
import com.fork.forkrpcall.common.util.URIUtils;
import com.fork.forkrpcall.rpc.Invoker;
import com.fork.forkrpcall.rpc.RpcInvocation;
import com.fork.forkrpcall.rpc.protocol.Protocol;
import com.fork.forkrpcall.remoting.Transporter;
import com.fork.forkrpcall.rpc.protocol.frpc.codec.FrpcCodec;
import com.fork.forkrpcall.rpc.protocol.frpc.handler.FrpcServerHandler;

import java.net.URI;

public class FrpcProtocol implements Protocol {
    @Override
    public void export(URI exportUri, Invoker invoker) {

        //找到序列化规则
        String serializationName = URIUtils.getParam(exportUri, "serialization");
        Serialization serialization = (Serialization) SpiUtils.getServiceImpl(serializationName, Serialization.class);

        //编解码器
        FrpcCodec frpcCodec = new FrpcCodec();
        frpcCodec.setDecodeType(RpcInvocation.class);
        frpcCodec.setSerialization(serialization);

        //收到请求处理器
        FrpcServerHandler frpcServerHandler = new FrpcServerHandler();
        frpcServerHandler.setInvoker(invoker);
        frpcServerHandler.setSerialization(serialization);

        //底层网络框架
        String transporterName = URIUtils.getParam(exportUri, "transporter");
        System.out.println("URI 解析用工具出来的结果：" + transporterName);
        Transporter transporter = (Transporter) SpiUtils.getServiceImpl(transporterName, Transporter.class);

        //启动服务
        transporter.start(exportUri,frpcCodec,frpcServerHandler);
    }

    @Override
    public Invoker refer(URI consumerUri) {
        return null;
    }
}
