package com.fork.forkrpcall.remoting;

import com.fork.forkrpcall.remoting.netty.NettyTransporter;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class remoting {
    public static void main(String[] args) throws URISyntaxException {
        new NettyTransporter().start(new URI("TRPP://127.0.0.1:8080"),
                new Codec() {
                    @Override
                    public byte[] encode(Object msg) throws Exception {
                        System.out.println("encode");
                        return new byte[0];
                    }

                    @Override
                    public List<Object> decode(byte[] message) throws Exception {
                        List<Object> objects = new ArrayList<>();
                        System.out.println("打印请求的内容：" + new String(message));
                        objects.add("1:" +new String(message) );
                        objects.add("2:" +new String(message));
                        objects.add("3:" +new String(message));
                        return objects;
                    }

                    @Override
                    public Codec createInstance() {
                        return this;
                    }
                },
                new Handler() {
                    @Override
                    public void onReceive(FRpcChannel trpcChannel, Object message) throws Exception {
                        System.out.println("handler on Receive中");
                        System.out.println(message);
                    }

                    @Override
                    public void onWrite(FRpcChannel trpcChannel, Object message) throws Exception {
                        System.out.println("handler on write中");

                    }
                });
    }
}
