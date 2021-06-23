package com.fork.forkrpcall.rpc.protocol.frpc;

import com.fork.forkrpcall.common.util.SpiUtils;
import com.fork.forkrpcall.common.util.URIUtils;
import com.fork.forkrpcall.rpc.Invoker;
import com.fork.forkrpcall.rpc.protocol.Protocol;
import com.fork.forkrpcall.tools.Transporter;

import java.net.URI;

public class FrpcProtocol implements Protocol {
    @Override
    public void export(URI exportUri, Invoker invoker) {
        String transporterName = URIUtils.getParam(exportUri, "transporter");
        System.out.println("URI 解析用工具出来的结果：" + transporterName);
        Transporter transporter = (Transporter) SpiUtils.getServiceImpl(transporterName, Transporter.class);

    }

    @Override
    public Invoker refer(URI consumerUri) {
        return null;
    }
}
