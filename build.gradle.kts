plugins {
    id("java")
    id("io.qameta.allure") version "3.0.0" // allure-framework/allure-gradle
}

group = "qa.vikindor"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("com.codeborne:selenide:7.10.0")
    testImplementation(platform("org.junit:junit-bom:6.0.0-M2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("io.qameta.allure:allure-selenide:2.30.0")
    // JUnit launcher
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    // SLF4J SimpleLogger
    testRuntimeOnly("org.slf4j:slf4j-simple:2.0.17")
    // !!! Critical for allureServe task on allure-gradle version 3+ !!!
    allureRawResultElements(files(layout.buildDirectory.dir("allure-results")))
}

allure {
    report {
        version.set("2.35.1") // allure-framework/allure2
    }
    adapter { // responsible for creating the build/allure-results folder
        autoconfigure.set(true)
        autoconfigureListeners.set(true)
        aspectjWeaver.set(true) // processes the @Step annotation
        frameworks {
            junit5 { // framework name
                adapterVersion.set("2.30.0") // allure-framework/allure-java
            }
        }
    }
}

tasks.test {
    useJUnitPlatform()
    // Redirect SLF4J SimpleLogger output from stderr (default, shown in red in IntelliJ)
    // to stdout so that log messages appear in the normal "gray" console color.
    jvmArgs("-Dorg.slf4j.simpleLogger.logFile=System.out")
    // Disable Selenium Manager telemetry (statistics sent to plausible.io)
    environment("SE_AVOID_STATS", "true")
}