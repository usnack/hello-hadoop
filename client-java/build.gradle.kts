plugins {
    id("java")
}

group = "com.hello.hadoop"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("log4j:log4j:1.2.17")
// https://mvnrepository.com/artifact/org.apache.hadoop/hadoop-client
    implementation("org.apache.hadoop:hadoop-client:3.3.6")

}

tasks.test {
    useJUnitPlatform()
}