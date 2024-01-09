
package com.poc.server;


import com.poc.handler.AcceptCompletionHandler;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;

public class Proactor implements Runnable {
    private final AsynchronousServerSocketChannel serverSocketChannel;

    public Proactor(final int port) {
        try {
            this.serverSocketChannel = AsynchronousServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress("localhost", port));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        final AcceptCompletionHandler acceptCompletionHandler = new AcceptCompletionHandler(serverSocketChannel);
        serverSocketChannel.accept(null, acceptCompletionHandler);
    }
}
