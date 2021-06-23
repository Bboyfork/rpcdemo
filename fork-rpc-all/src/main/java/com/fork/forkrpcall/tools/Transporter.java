package com.fork.forkrpcall.tools;

import java.net.URI;

/**
 * 底层网络传输 —— 统一入口
 * */
public interface Transporter {
    Server start(URI uri, Codec codec, Handler handler);
}
