package com.fork.forkrpcall.rpc.protocol.frpc.codec;

import com.fork.forkrpcall.common.util.ByteUtil;
import com.fork.forkrpcall.common.serialize.Serialization;
import com.fork.forkrpcall.remoting.Codec;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.ArrayList;
import java.util.List;

/**
 * 和netty中的 NettyCodec 的简单实现不同，
 * 此处完整的定义了消息的规则，包括头部、长度、以及粘包拆包的实现。
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
            byteBuf.writeBytes(tempMsg);
            byteBuf.writeBytes(message);
        }else {
            byteBuf.writeBytes(message);
        }

        for(;;){
            //如果数据太少，不够一个头部，等待处理。
            if(HEADER_LEN >= byteBuf.readableBytes()){
                tempMsg.clear();
                tempMsg.writeBytes(byteBuf);
                return out;
            }

            //数据够，那就处理：
            //解析数据：     检查关键字
           byte[] magic = new byte[2];
            byteBuf.readBytes(magic);
            for(;;){
                //匹配头部，  得从头开始读数据嘛。。。
                if(magic[0] != MAGIC[0] || magic[1] != MAGIC[1]){
                    if(byteBuf.readableBytes() == 0){ //读到剩余为0了。 数据读完都没发现正确的头部。就gg
                        tempMsg.clear();
                        tempMsg.writeByte(magic[1]);
                        return out;
                    }
                    magic[0] = magic[1];
                    magic[1] = byteBuf.readByte();
                }else {
                    break;
                }
            }

            //一个int 4字节  这里是我们设定的， 长度位
            byte[] lengthBytes = new byte[4];
            byteBuf.readBytes(lengthBytes);
            int length = ByteUtil.Bytes2Int_BE(lengthBytes);

            if(byteBuf.readableBytes() < length){
                tempMsg.clear();
                tempMsg.writeBytes(magic);
                tempMsg.writeBytes(lengthBytes);
                tempMsg.writeBytes(byteBuf);
                return out;
            }

            byte[] body = new byte[length];
            byteBuf.readBytes(body);

            //序列化
            Object o = getSerialization().deserialize(body,decodeType);
            out.add(o);
        }


    }

    @Override
    public Codec createInstance() {
        FrpcCodec frpcCodec = new FrpcCodec();
        frpcCodec.setDecodeType(this.decodeType);
        frpcCodec.setSerialization(this.serialization);
        return frpcCodec;
    }

    Class decodeType;

    Serialization serialization;

    public Class getDecodeType() {
        return decodeType;
    }

    public void setDecodeType(Class decodeType) {
        this.decodeType = decodeType;
    }

    public Serialization getSerialization() {
        return serialization;
    }

    public void setSerialization(Serialization serialization) {
        this.serialization = serialization;
    }
}
