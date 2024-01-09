package com.lab.sc.scope;

import com.lab.sc.scope.user.User;

import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.ThreadFactory;

public class ScopeVariableRunner {
    public static final ScopedValue<User> user = ScopedValue.newInstance();

    public static void main(String[] args) throws Exception {
        ScopedValue
                .where(user, new User("sally"))
                .call(ScopeVariableRunner::invokeTaskScope);
    }

    private static String invokeTaskScope() throws Exception {
        ThreadFactory factory = Thread.ofVirtual().name("test-", 0).factory();
        try (var scope = new StructuredTaskScope<String>("test-scope", factory)) {

            scope.fork(() -> {
                User reqUser = user.orElse(new User("anonymous"));
                print("invokeTaskScope - user " + reqUser);

                // set the Id for the user
                reqUser.setId("bob");

                return "done";
            });

            scope.join();
        }

        User reqUser = user.orElse(new User("anonymous"));
        print("invokeTaskScope - user " + reqUser);
        return "done";
    }

    public static void print(String m) {
        System.out.printf("[%s] %s\n", Thread.currentThread().getName(), m);
    }
}