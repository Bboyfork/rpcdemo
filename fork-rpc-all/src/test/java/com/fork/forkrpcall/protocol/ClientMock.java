package com.fork.forkrpcall.protocol;

import com.fork.forkrpcall.common.serialize.Serialization;
import com.fork.forkrpcall.common.util.ByteUtil;
import com.fork.forkrpcall.common.util.SpiUtils;
import com.fork.forkrpcall.rpc.RpcInvocation;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 模拟客户端
 * */
public class ClientMock {
    public static void main(String[] args) throws Exception {
        RpcInvocation rpcInvocation = new RpcInvocation();

        rpcInvocation.setServiceName("com.rpcfork.api.sms.SmsService");
        rpcInvocation.setMethodName("send");
        rpcInvocation.setParameterTypes(new Class[]{String.class,String.class});
        rpcInvocation.setArguments(new Object[]{"10086","短信。。tmd。。。"});
        Serialization serialization = (Serialization) SpiUtils.getServiceImpl("JsonSerialization", Serialization.class);

        byte[] requestBody = serialization.serialize(rpcInvocation);

        //构建
        ByteBuf requestBuffer = Unpooled.buffer();
        requestBuffer.writeByte(0xda);
        requestBuffer.writeByte(0xbb);
        requestBuffer.writeBytes(ByteUtil.int2bytes(requestBody.length));
        requestBuffer.writeBytes(requestBody);

        //发起请求
        SocketChannel frpcClient = SocketChannel.open();
        frpcClient.connect(new InetSocketAddress("127.0.0.1",8080));
        frpcClient.write(ByteBuffer.wrap(requestBuffer.array()));

        //接收响应
        ByteBuffer response = ByteBuffer.allocate(1025);
        frpcClient.read(response);


    }
}
