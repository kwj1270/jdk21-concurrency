package com.poc;

import com.poc.server.Proactor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProactorApplicationServer {

    private static final Logger logger = LoggerFactory.getLogger(ProactorApplicationServer.class);


    public static void main(String[] args) {
        logger.info(">>> start main");
        final Proactor proactor = new Proactor(8080);
        proactor.run();
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("<<< start end");
    }
}
