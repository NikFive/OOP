import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm")
    kotlin("plugin.allopen") version "1.7.20"
    id("org.jetbrains.kotlinx.benchmark") version "0.4.7"
}

group = "ru.nsu.fit.konstantinov.task-2-1-1"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

sourceSets.all {
    java.setSrcDirs(listOf("$name/src"))
    resources.setSrcDirs(listOf("$name/resources"))
}

configure<org.jetbrains.kotlin.allopen.gradle.AllOpenExtension> {
    annotation("org.openjdk.jmh.annotations.State")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

benchmark {
    configurations {
        named("main") {
            iterationTime = 5
            iterationTimeUnit = "sec"

        }
    }
    targets {
        register("main") {
            this as kotlinx.benchmark.gradle.JvmBenchmarkTarget
            jmhVersion = "1.21"
        }
    }
}
