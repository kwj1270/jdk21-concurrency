package com.lab.sc;

import com.lab.sc.clients.CurrencyClients;
import com.lab.sc.clients.UserClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.StructuredTaskScope;

public class StructuredConcurrencyNestedApplication {
    private static final Logger log = LoggerFactory.getLogger(StructuredConcurrencyNestedApplication.class);

    public static void main(String[] args) {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            final var currencyPairSubTask = scope.fork(() -> CurrencyClients.currencyPair(1000L, "btc_krw"));
            final var userSubTask = scope.fork(() -> UserClients.info(2000L, UUID.randomUUID()));
            log.info("currencyPair state =  {}, userSubTask state = {}", currencyPairSubTask.state(), userSubTask.state());
            try (var nestedScope = new StructuredTaskScope.ShutdownOnSuccess<>()) {
                final var btcKrw2 = nestedScope.fork(() -> CurrencyClients.currencyPair(1000L, "btc_krw"));
                final var user2 = nestedScope.fork(() -> UserClients.info(2000L, UUID.randomUUID()));
                nestedScope.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            scope.join();
            final String currencyPair = currencyPairSubTask.get();
            final String userUuid = userSubTask.get();
            log.info("currencyPair = {}, userUuid = {}", currencyPair, userUuid);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
