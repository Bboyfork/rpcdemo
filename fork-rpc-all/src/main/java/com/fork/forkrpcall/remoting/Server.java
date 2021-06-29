package com.fork.forkrpcall.remoting;

import java.net.URI;

public interface Server {
    void start(URI uri, Codec codec, Handler handler);
}
