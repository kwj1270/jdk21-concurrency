package com.lab.sc.virtualthread;

import java.util.concurrent.CompletableFuture;

public class VirtualThreadRunner {
    public static void main(String[] args) {
        try (var virtualThread = VirtualThreadCreator.virtualThread()) {
            CompletableFuture.supplyAsync(Thread::currentThread, virtualThread)
                    .thenAcceptAsync(System.out::println);
        }
    }
}
