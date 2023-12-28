package com.lab.concurrency.virtualthread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadCreator {
    public static ExecutorService virtualThread() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }
}
