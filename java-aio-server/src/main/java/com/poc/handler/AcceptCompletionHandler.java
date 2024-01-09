package com.poc.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, Void> {

    private static final Logger logger = LoggerFactory.getLogger(AcceptCompletionHandler.class);

    private final AsynchronousServerSocketChannel serverSocketChannel;

    public AcceptCompletionHandler(final AsynchronousServerSocketChannel serverSocketChannel) {
        this.serverSocketChannel = serverSocketChannel;
    }

    @Override
    public void completed(final AsynchronousSocketChannel socketChannel, final Void attachment) {
        serverSocketChannel.accept(null, this);
        new HttpCompletionHandler(socketChannel);
    }

    @Override
    public void failed(final Throwable exc, final Void attachment) {
        logger.error("Failed to accept Connection", exc);
    }
}
