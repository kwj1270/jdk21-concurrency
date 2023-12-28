package com.lab.concurrency.scope.threadlocal;

public class InheritableThreadLocalBox {
    private static final ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
}
