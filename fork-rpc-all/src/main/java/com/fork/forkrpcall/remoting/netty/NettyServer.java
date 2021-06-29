package com.fork.forkrpcall.remoting.netty;

import com.fork.forkrpcall.remoting.Codec;
import com.fork.forkrpcall.remoting.Handler;
import com.fork.forkrpcall.remoting.Server;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.net.URI;

public class NettyServer implements Server {

    EventLoopGroup boss = new NioEventLoopGroup();
    EventLoopGroup worker = new NioEventLoopGroup();

    @Override
    public void start(URI uri, Codec codec, Handler handler) {
        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss,worker)
                    //绑定通道
                    .channel(NioServerSocketChannel.class)
                    //绑定地址
                    .localAddress(new InetSocketAddress(uri.getHost(),uri.getPort()))
                    //添加headler
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {

                            //编解码器  createInstance 来保证每次调用的都不是一个编解码器 来保证线程安全
                            channel.pipeline().addLast(new NettyCodec(codec.createInstance()));
                            //网络
                            channel.pipeline().addLast(new NettyHandler(handler));
                        }
                    });
            ChannelFuture future = bootstrap.bind().sync();
            System.out.println("netty server 启动成功");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
