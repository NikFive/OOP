import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    id("org.jetbrains.dokka") version "1.7.20"
    jacoco
    application
}

group = "ru.nsu.fit.konstantinov.task-2-2-1"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    dokkaJavadocPlugin("org.jetbrains.dokka:kotlin-as-java-plugin:1.7.20")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.11.+")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0-RC")
    implementation ("com.google.code.gson:gson:2.8.5")
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

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.dokkaJavadoc.configure {
    outputDirectory.set(file("javadoc"))
}

application {
    mainClass.set("MainKt")
}
