package com.lab.concurrency.strutcturedconcurrency;

import com.lab.concurrency.strutcturedconcurrency.clients.CurrencyClients;
import com.lab.concurrency.strutcturedconcurrency.clients.UserClients;

import java.util.UUID;

public class StructuredConcurrencyRunner {
    public static void main(String[] args) {
        CurrencyClients.currencyPair("btc_krw");
        UserClients.info(UUID.randomUUID());
    }
}
