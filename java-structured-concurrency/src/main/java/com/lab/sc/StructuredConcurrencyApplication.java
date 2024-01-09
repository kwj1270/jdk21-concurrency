package com.lab.sc;

import com.lab.sc.clients.CurrencyClients;
import com.lab.sc.clients.UserClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.StructuredTaskScope;

public class StructuredConcurrencyApplication {
    private static final Logger log = LoggerFactory.getLogger(StructuredConcurrencyApplication.class);

    public static void main(String[] args) {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            final long start = System.currentTimeMillis();
            final var currencyPairSubTask = scope.fork(() -> CurrencyClients.currencyPair(1000L, "btc_krw"));
            final var userSubTask = scope.fork(() -> UserClients.info(2000L, UUID.randomUUID()));
            scope.join();
            log.info("currencyPair state =  {}, userSubTask state = {}", currencyPairSubTask.state(), userSubTask.state());
            final String currencyPair = currencyPairSubTask.get();
            final String userUuid = userSubTask.get();
            final long end = System.currentTimeMillis();
            System.out.println("time = " + (end - start));
            log.info("currencyPair = {}, userUuid = {}", currencyPair, userUuid);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
