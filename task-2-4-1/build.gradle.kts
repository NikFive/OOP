import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm")
    kotlin("plugin.allopen") version "1.7.20"
    id("org.jetbrains.kotlinx.benchmark") version "0.4.7"
    id("org.jetbrains.dokka") version "1.7.20"
    jacoco
}

group = "ru.nsu.fit.konstantinov.task-2-4-1"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://repo.gradle.org/gradle/libs-releases") }
}

sourceSets.all {
    java.setSrcDirs(listOf("$name/src"))
    resources.setSrcDirs(listOf("$name/resources"))
}

sourceSets.getByName("test") {
    dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime:0.4.7")
        implementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime-jvm:0.4.7")
    }
}

configure<org.jetbrains.kotlin.allopen.gradle.AllOpenExtension> {
    annotation("org.openjdk.jmh.annotations.State")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    dokkaJavadocPlugin("org.jetbrains.dokka:kotlin-as-java-plugin:1.7.20")
    implementation("org.jetbrains.kotlin:kotlin-script-util")
    implementation("org.jetbrains.kotlin:kotlin-compiler-embeddable")
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.5")
    implementation("org.jetbrains.kotlinx:kotlinx-html:0.7.5")
    implementation(kotlin("reflect"))
    implementation(kotlin("script-runtime"))
    implementation(kotlin("compiler-embeddable"))
    implementation(kotlin("script-util"))
    implementation(kotlin("scripting-compiler-embeddable"))
    implementation("org.eclipse.jgit:org.eclipse.jgit:6.1.0.202203080745-r")
    implementation("org.gradle:gradle-tooling-api:7.4")
    runtimeOnly("org.slf4j:slf4j-simple:1.7.36")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

benchmark {
    configurations {
        named("main") {
            mode = "avgt"
        }
    }
    targets {
        register("test") {
            this as kotlinx.benchmark.gradle.JvmBenchmarkTarget
            jmhVersion = "1.21"
        }
    }
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        csv.required.set(false)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = "0.5".toBigDecimal()
            }
        }

        rule {
            isEnabled = false
            element = "CLASS"
            includes = listOf("org.gradle.*")

            limit {
                counter = "LINE"
                value = "TOTALCOUNT"
                maximum = "0.3".toBigDecimal()
            }
        }
    }
}

jacoco {
    toolVersion = "0.8.8"
}
tasks.dokkaJavadoc.configure {
    outputDirectory.set(file("javadoc"))
}
