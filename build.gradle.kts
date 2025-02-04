plugins {
    kotlin("jvm") version "2.0.0"
}

group = "com.momid"
version = "1.0"

repositories {
    mavenCentral()
    maven { setUrl("https://jitpack.io") }
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.github.momid3:TraDeep:0.3.8")
    implementation(project(":ParserRules"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(16)
}