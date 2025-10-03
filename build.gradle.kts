plugins {
    id("java")
    id("io.qameta.allure") version "3.0.0"
}

group = "qa.vikindor"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("com.codeborne:selenide:7.10.1")
    testImplementation(platform("org.junit:junit-bom:6.0.0-M2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("io.qameta.allure:allure-selenide:2.30.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testRuntimeOnly("org.slf4j:slf4j-simple:2.0.17")
    allureRawResultElements(files(layout.buildDirectory.dir("allure-results")))
}

allure {
    report {
        version.set("2.35.1")
    }
    adapter {
        autoconfigure.set(true)
        autoconfigureListeners.set(true)
        aspectjWeaver.set(true)
        frameworks {
            junit5 {
                adapterVersion.set("2.30.0")
            }
        }
    }
}

tasks.test {
    useJUnitPlatform()
    systemProperties(
        System.getProperties()
            .entries
            .associate { (k, v) -> k.toString() to v }
    )
    jvmArgs("-Dorg.slf4j.simpleLogger.logFile=System.out")
    environment("SE_AVOID_STATS", "true")
}
