package com.lab.sc.clients;

import java.util.UUID;

public class UserClients {

    /***
     * @param blockTime
     * @param userUuid
     * @return
     * @throws InterruptedException
     */
    public static String info(final Long blockTime, final UUID userUuid) throws InterruptedException {
        Thread.sleep(blockTime);
        return userUuid.toString();
    }
}
