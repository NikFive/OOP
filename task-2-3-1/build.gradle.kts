import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    id("org.jetbrains.dokka") version "1.7.20"
    id("org.openjfx.javafxplugin") version "0.0.13"
    jacoco
    application
}

javafx {
    version = "12"
    modules("javafx.controls", "javafx.fxml")
}

group = "ru.nsu.fit.konstantinov.task-2-3-1"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    dokkaJavadocPlugin("org.jetbrains.dokka:kotlin-as-java-plugin:1.7.20")
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
    mainClass.set("ru.nsu.fit.konstantinov.task_2_3_1.Game")
}


