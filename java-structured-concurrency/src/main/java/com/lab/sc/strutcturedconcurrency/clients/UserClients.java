package com.lab.concurrency.strutcturedconcurrency.clients;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class UserClients {
    public static CompletableFuture<String> info(final Long blockTime, final UUID userUuid) throws InterruptedException {
        Thread.sleep(blockTime);
        return CompletableFuture.supplyAsync(() -> userUuid.toString());
    }
}
