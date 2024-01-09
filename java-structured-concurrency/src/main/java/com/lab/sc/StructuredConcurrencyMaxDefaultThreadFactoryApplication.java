package com.lab.sc;

import com.lab.sc.clients.CurrencyClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.IntStream;

public class StructuredConcurrencyMaxDefaultThreadFactoryApplication {
    private static final Logger log = LoggerFactory.getLogger(StructuredConcurrencyMaxDefaultThreadFactoryApplication.class);

    public static void main(String[] args) {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure("hello", Executors.defaultThreadFactory())) {
            final long start = System.currentTimeMillis();
            final List<StructuredTaskScope.Subtask<String>> subtaskList = IntStream.range(0, 10000)
                    .mapToObj(operand -> scope.fork(() -> {
                                log.info("[{}], currencyPairSubTask", Thread.currentThread());
                                return CurrencyClients.currencyPair(3000L, "btc_krw");
                            })
                    )
                    .toList();

            scope.join();
            Executors.newFixedThreadPool(1);
            subtaskList.forEach(stringSubtask -> log.info("currencyPair state =  {}", stringSubtask.state()));
            subtaskList.forEach(stringSubtask -> log.info("currencyPair  =  {}", stringSubtask.get()));
            subtaskList.forEach(stringSubtask -> stringSubtask.get());
            final long end = System.currentTimeMillis();
            System.out.println("time = " + (end - start));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
