package com.lab.vt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JavaHttpVirtualThreadApplication {

    private static final Logger log = LoggerFactory.getLogger(JavaHttpVirtualThreadApplication.class);
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public static void main(String[] args) {
        final var request = HttpRequest.newBuilder().GET()
                .uri(URI.create("http://localhost:8080"))
                .build();

        try (final ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 10; i++) {
                executorService.execute(() -> {
                    try {
                        final Thread thread = Thread.currentThread();
                        log.info("[{}], {}", thread, "is Virtual Thread?");
                        final HttpResponse<String> send = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                        log.info("Response Body = {}", send.body());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            log.info("end");
        }
    }
}