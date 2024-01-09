package com.lab.sc;

import com.lab.sc.clients.CurrencyClients;
import com.lab.sc.clients.UserClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.StructuredTaskScope;

public class StructuredConcurrencyDefaultThreadFactoryApplication {
    private static final Logger log = LoggerFactory.getLogger(StructuredConcurrencyDefaultThreadFactoryApplication.class);

    public static void main(String[] args) {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure("hello", Executors.defaultThreadFactory())) {
            final long start = System.currentTimeMillis();
            final var currencyPairSubTask = scope.fork(() -> {
                log.info("[{}], currencyPairSubTask", Thread.currentThread());
                return CurrencyClients.currencyPair(3000L, "btc_krw");
            });
            final var userSubTask = scope.fork(() -> {
                log.info("[{}], userClients", Thread.currentThread());
                return UserClients.info(2000L, UUID.randomUUID());
            });
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
