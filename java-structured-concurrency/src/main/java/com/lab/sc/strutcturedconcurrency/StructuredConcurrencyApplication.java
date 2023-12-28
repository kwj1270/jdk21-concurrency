package com.lab.concurrency.strutcturedconcurrency;

import com.lab.concurrency.strutcturedconcurrency.clients.CurrencyClients;
import com.lab.concurrency.strutcturedconcurrency.clients.UserClients;

import java.util.UUID;
import java.util.concurrent.StructuredTaskScope;

public class StructuredConcurrencyApplication {

    public static void main(String[] args) {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            final var btcKrw = scope.fork(() -> CurrencyClients.currencyPair(1000L, "btc_krw"));
            final var user = scope.fork(() -> UserClients.info(2000L, UUID.randomUUID()));
            scope.join();
            System.out.println(btcKrw.state());
            System.out.println(user.state());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
