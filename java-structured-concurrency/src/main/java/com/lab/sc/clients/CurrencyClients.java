package com.lab.sc.clients;

import java.util.UUID;

public class CurrencyClients {
    /***
     * @param blockTime
     * @param currencyPair
     * @return
     * @throws InterruptedException
     */
    public static String currencyPair(final long blockTime, final String currencyPair) throws InterruptedException {
        Thread.sleep(blockTime);
        return UUID.randomUUID().toString();
    }
}
