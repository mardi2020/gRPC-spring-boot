### gRPC server
기본적으로 gRPC는 9090 포트를 사용한다.

#### 1. IDL jar 파일을 포함시킨다.
jar 파일 경로: ./libs/
```groovy
    implementation files('libs/Interface-1.0-SNAPSHOT.jar')
```

#### 2. build.gradle에 gRPC 통신에 필요한 dependency 추가
```groovy
plugins {
    id 'java'
    id 'org.springframework.boot' version '2.7.7'
    id 'io.spring.dependency-management' version '1.0.15.RELEASE'
    id 'com.google.protobuf' version "0.8.14" // 추가
}

group = 'com.example'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '11'

configurations {
    compileOnly {
        extendsFrom annotationProcessor
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter'
    implementation 'link.jfire:simplerpc:1.0' // 추가
    implementation 'io.github.HuChen-dot:simplerpc-spring-boot-starter:1.6' // 추가
    implementation 'net.devh:grpc-server-spring-boot-starter:2.13.1.RELEASE' // 추가
    implementation files('libs/Interface-1.0-SNAPSHOT.jar')
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    implementation "io.grpc:grpc-protobuf:1.51.0" // 추가
}

tasks.named('test') {
    useJUnitPlatform()
}
```

#### 3. GrpcService에서 SayHello 메서드 구현
IDL에 생성된 CustomServiceImplBase를 상속받아 sayHello 메서드를 구현한다.

```java
@GrpcService
public class Service extends CustomServiceImplBase {
    @Override
    public void sayHello(CustomRequest request, StreamObserver<CustomResponse> responseObserver) {
        CustomResponse response = CustomResponse.newBuilder()
                .setMessage("Hello, " + request.getName())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
```
