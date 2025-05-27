// 플러그인 설정을 시작하는 블록입니다.
// 플러그인은 Gradle의 기능을 확장하여 다양한 작업을 수행할 수 있도록 합니다. (예: Kotlin 컴파일, Spring Boot 패키징)
plugins {
    // Kotlin을 JVM에서 실행할 수 있도록 하는 플러그인입니다. "1.9.25"는 Kotlin 버전을 의미합니다.
    kotlin("jvm") version "1.9.25"

    // Spring 프레임워크와 Kotlin을 함께 사용하기 위한 플러그인입니다.
    // 예를 들어, Spring의 @Component 같은 어노테이션이 붙은 클래스를 Kotlin에서 기본적으로 final이 아닌 open으로 만들어줍니다.
    kotlin("plugin.spring") version "1.9.25"

    // Spring Boot 애플리케이션을 쉽게 빌드하고 실행할 수 있도록 다양한 기능을 제공하는 플러그인입니다.
    // 실행 가능한 JAR 파일 생성, 의존성 버전 관리 등을 담당합니다. "3.5.0"은 Spring Boot 버전을 의미합니다.
    id("org.springframework.boot") version "3.5.0"

    // Spring Boot가 권장하는 의존성 버전들을 관리해주는 플러그인입니다.
    // 이를 통해 개발자는 각 라이브러리의 호환되는 버전을 일일이 찾을 필요 없이 Spring Boot가 관리하는 버전을 사용하게 됩니다.
    id("io.spring.dependency-management") version "1.1.7"
}

// 프로젝트의 그룹 ID를 설정합니다. 보통 회사의 도메인명을 역순으로 사용합니다. (예: com.example)
group = "com.wonhyung"
// 프로젝트의 버전입니다. "SNAPSHOT"은 아직 개발 중인 버전을 의미합니다.
version = "0.0.1-SNAPSHOT"

// Java 관련 설정을 하는 블록입니다.
java {
    // Java 컴파일러 및 런타임 환경에 대한 설정을 합니다.
    toolchain {
        // 사용할 Java 언어 버전을 설정합니다. 여기서는 Java 21을 사용하도록 지정했습니다.
        languageVersion = JavaLanguageVersion.of(21)
    }
}

// 의존성을 다운로드할 저장소를 설정하는 블록입니다.
repositories {
    // Maven Central 저장소를 사용합니다. 가장 널리 사용되는 공개 라이브러리 저장소 중 하나입니다.
    mavenCentral()
}

// 프로젝트에 필요한 라이브러리(의존성)들을 선언하는 블록입니다.
dependencies {
    // Spring Boot에서 JDBC를 사용하여 데이터베이스에 더 쉽게 접근할 수 있도록 하는 스타터 의존성입니다.
    // Connection Pool (HikariCP 기본) 등을 포함합니다.
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")

    // Spring MVC를 사용하여 웹 애플리케이션(REST API 포함)을 만들 수 있도록 하는 스타터 의존성입니다.
    // 내장 웹 서버(기본 Tomcat)를 포함합니다.
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Kotlin 데이터 클래스 등을 Jackson JSON 라이브러리가 처리할 수 있도록 지원하는 모듈입니다.
    // (JSON <-> Kotlin 객체 변환)
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Kotlin 리플렉션 라이브러리입니다. Kotlin 클래스 및 프로퍼티 정보를 런타임에 조회할 수 있게 합니다.
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // 개발 환경에서만 사용되는 의존성입니다.
    // 애플리케이션의 코드가 변경되면 자동으로 재시작(LiveReload)하거나, 개발 시 유용한 기능을 제공합니다.
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // H2 데이터베이스를 런타임에 사용할 수 있도록 하는 의존성입니다. H2는 인메모리 DB로, 개발 및 테스트용으로 많이 사용됩니다.
    runtimeOnly("com.h2database:h2")

    // MySQL 데이터베이스에 연결하기 위한 JDBC 드라이버입니다.
    runtimeOnly("com.mysql:mysql-connector-j")

    // Spring Boot 애플리케이션 테스트를 위한 스타터 의존성입니다. JUnit, Mockito 등을 포함합니다.
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // Kotlin 코드를 JUnit 5 환경에서 테스트할 수 있도록 지원하는 라이브러리입니다.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

    // JUnit Platform을 실행하기 위한 런처입니다. 테스트 시에만 필요합니다.
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // Thymeleaf 템플릿 엔진을 Spring Boot에서 사용하기 위한 스타터 의존성입니다.
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    // MyBatis를 Spring Boot에서 쉽게 사용할 수 있도록 하는 스타터 의존성입니다.
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.3")
}

// Kotlin 컴파일러 관련 옵션을 설정하는 블록입니다.
kotlin {
    compilerOptions {
        // Kotlin 컴파일러에 전달할 추가 인자를 설정합니다.
        // "-Xjsr305=strict"는 JSR-305 어노테이션(@Nullable, @NonNull 등)을 엄격하게 처리하도록 설정합니다.
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

// 특정 타입의 태스크에 대한 설정을 하는 블록입니다.
// 여기서는 'Test' 타입의 모든 태스크에 대해 설정을 적용합니다.
tasks.withType<Test> {
    // JUnit Platform을 사용하여 테스트를 실행하도록 설정합니다. (JUnit 5 사용 시 필요)
    useJUnitPlatform()
}