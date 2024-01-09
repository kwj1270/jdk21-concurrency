package com.poc.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

    private static final Logger logger = LoggerFactory.getLogger(Client.class);
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(50);

    public static void main(String[] args) {
        final List<CompletableFuture> futures = new ArrayList<>();
        long start = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            final CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try (final Socket socket = new Socket()) {
                    socket.connect(new InetSocketAddress("localhost", 8080));

                    final OutputStream outputStream = socket.getOutputStream();
                    outputStream.write("This is Socket Client".getBytes(StandardCharsets.US_ASCII));
                    outputStream.flush();

                    final InputStream inputStream = socket.getInputStream();
                    final byte[] bytes = new byte[1024];
                    inputStream.read(bytes);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }, EXECUTOR_SERVICE);
            futures.add(future);
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        EXECUTOR_SERVICE.shutdown();
        long end = System.currentTimeMillis();
        logger.info("RPS = {} ms", (end - start) / 1000.0);
    }
}
