import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

plugins {
    java
}

repositories {
    mavenCentral()
    mavenLocal()
    maven {
        url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

val minSonarQubeVersion = "9.9.0.65466"
val minSonarQubeApiVersion = "9.14.0.375"

dependencies {
    compileOnly("org.sonarsource.api.plugin:sonar-plugin-api:$minSonarQubeApiVersion")
    compileOnly("com.felipebz.zpa:sonar-zpa-plugin:3.7.0-SNAPSHOT")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.0")
    testImplementation("org.sonarsource.sonarqube:sonar-plugin-api-impl:$minSonarQubeVersion")
    testImplementation("com.felipebz.zpa:zpa-checks-testkit:3.7.0-SNAPSHOT")
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.3")
}

configurations {
    // include compileOnly dependencies during test
    testImplementation {
        extendsFrom(configurations.compileOnly.get())
    }
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.jar {
    manifest {
        val buildDate = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ").withZone(ZoneId.systemDefault()).format(
            Date().toInstant())
        attributes(mapOf(
            "Plugin-BuildDate" to buildDate,
            "Plugin-ChildFirstClassLoader" to "false",
            "Plugin-Class" to "com.jonathanmborges.plsql.XPathCustomRulesPlugin",
            "Plugin-Description" to "ZPA XPath Custom PL/SQL Rules",
            "Plugin-Developers" to "https://github.com/jonathan-m-borges",
            "Plugin-Display-Version" to project.version,
            "Plugin-Key" to "zpa-xpath-rules",
            "Plugin-License" to "",
            "Plugin-Name" to "ZPA XPath Custom PL/SQL Rules",
            "Plugin-Version" to project.version,
            "Sonar-Version" to minSonarQubeVersion,
            "SonarLint-Supported" to "false",
            "Plugin-RequiredForLanguages" to "plsqlopen"
        ))
    }
}

group = "com.jonathanmborges"
version = "0.1"
description = "ZPA XPath Custom PL/SQL Rules"

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}