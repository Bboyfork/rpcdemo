package com.fork.forkrpcall.tools.netty;

import com.fork.forkrpcall.tools.Codec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

/**
 * 编解码
 * */
public class NettyCodec extends ChannelDuplexHandler {
    private Codec codec;
    public NettyCodec(Codec codec){this.codec = codec;}

    /**
     * 入栈事件(收到数据 请求/响应)
     * */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);
//        System.out.println("handler 内容： " + msg);
        ByteBuf data = (ByteBuf)msg;
        byte[] dataBytes = new byte[data.readableBytes()];
        data.readBytes(dataBytes);

        List<Object> out = codec.decode(dataBytes);

        for (Object o:out) {
            ctx.fireChannelRead(o);
        }
        System.out.println("内容" + msg);
    }
}
