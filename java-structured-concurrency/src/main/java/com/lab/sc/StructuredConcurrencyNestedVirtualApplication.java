package com.lab.sc;

import com.lab.sc.clients.CurrencyClients;
import com.lab.sc.clients.UserClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.StructuredTaskScope;

public class StructuredConcurrencyNestedVirtualApplication {
    private static final Logger log = LoggerFactory.getLogger(StructuredConcurrencyNestedVirtualApplication.class);

    public static void main(String[] args) {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            final var currencyPairSubTask = scope.fork(() -> CurrencyClients.currencyPair(1000L, "btc_krw"));
            final var userSubTask = scope.fork(() -> UserClients.info(2000L, UUID.randomUUID()));
            final var nestedSubTasks = scope.fork(() -> {
                try (var nestedScope = new StructuredTaskScope.ShutdownOnFailure()) {
                    final var currencyPairSubTask2 = nestedScope.fork(() -> CurrencyClients.currencyPair(1000L, "btc_krw"));
                    final var userSubTask2 = nestedScope.fork(() -> UserClients.info(2000L, UUID.randomUUID()));
                    log.info("currencyPairSubTask2 state =  {}, userSubTask2 state = {}", currencyPairSubTask2.state(), userSubTask2.state());

                    nestedScope.join();
                    log.info("currencyPairSubTask2 state =  {}, userSubTask2 state = {}", currencyPairSubTask2.state(), userSubTask2.state());

                    return currencyPairSubTask2.get() + userSubTask2.get();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            log.info("currencyPairSubTask state =  {}, userSubTask state = {}, nestedSubTasks state = {}", currencyPairSubTask.state(), userSubTask.state(), nestedSubTasks.state());

            scope.join();
            log.info("currencyPairSubTask state =  {}, userSubTask state = {}, nestedSubTasks state = {}", currencyPairSubTask.state(), userSubTask.state(), nestedSubTasks.state());
            final String currencyPair = currencyPairSubTask.get();
            final String userUuid = userSubTask.get();
            log.info("currencyPair = {}, userUuid = {}", currencyPair, userUuid);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
