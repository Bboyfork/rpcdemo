package com.fork.forkrpcall.remoting.netty;

import com.fork.forkrpcall.remoting.Codec;
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
     * 入栈时间(收到数据 请求/响应)
     * */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);
//        System.out.println("handler 内容： " + msg);
        ByteBuf date = (ByteBuf)msg;
        byte[] dataBytes = new byte[date.readableBytes()];
        List<Object> out = codec.decode(dataBytes);

        for (Object o:out) {
            ctx.fireChannelRead(o);
        }

    }
}
