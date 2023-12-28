package com.lab.concurrency.strutcturedconcurrency.clients;

import java.util.concurrent.CompletableFuture;

public class CurrencyClients {
    public static CompletableFuture<String> currencyPair(final long blockTime, final String currencyPair) throws InterruptedException {
        Thread.sleep(blockTime);
        return CompletableFuture.supplyAsync(() -> currencyPair);
    }
}
