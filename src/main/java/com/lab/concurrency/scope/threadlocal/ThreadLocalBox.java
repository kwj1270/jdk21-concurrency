package com.lab.concurrency.scope.threadlocal;

public class ThreadLocalBox {
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();
}
