package com.fork.forkrpcall.rpc.protocol.frpc.codec;

import com.fork.forkrpcall.tools.Codec;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * */
public class FrpcCodec implements Codec {

    /**
     * 设定了一系列的规则，包括：特殊请求头部： 0xdabb      业务数据长度  INT 4字节         业务数据：RpcInvocation 对象序列化
     * */
    public final static byte[] MAGIC = new byte[]{(byte) 0xda, (byte) 0xbb};
    /**
     * 协议头部长度
     */
    public static final int HEADER_LEN = 6;

    // 用来临时保留没有处理过的请求报文
    ByteBuf tempMsg = Unpooled.buffer();


    @Override
    public byte[] encode(Object msg) throws Exception {

        return new byte[0];
    }

    @Override
    public List<Object> decode(byte[] message) throws Exception {
        List<Object> out = new ArrayList<>();

        //解析(解析头部，取出数据，封装成invocation)
        //合并报文
        ByteBuf byteBuf = Unpooled.buffer();
        int tempMsgSize = tempMsg.readableBytes();
        if(tempMsgSize>0){
            byteBuf.writeBytes()
        }


        return out;
    }

    @Override
    public Codec createInstance() {
        FrpcCodec frpcCodec = new FrpcCodec();

        return null;
    }
}
