pluginManagement {
    plugins {
        kotlin("jvm") version "1.9.21"
    }
}
rootProject.name = "jdk21-concurrency"

include(
    "java-aio-server",
    "java-cps",
    "java-fsm",
    "java-fsmcps",
    "java-virtual-thread",
    "java-scoped-value",
    "java-structured-concurrency",
)