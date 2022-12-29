### gRPC client

정
```

#### 2. build.gradle에 gRPC 통신에 필요한 dependency 추가
```groovy
plugins {
    id 'java'
    id 'org.springframework.boot' version '2.7.7'
    id 'com.google.protobuf' version "0.8.14" // 추가
    id 'io.spring.dependency-management' version '1.0.15.RELEASE'
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
    implementation 'org.springframework.boot:spring-boot-starter-web'
    
    implementation files('libs/Interface-1.0-SNAPSHOT.jar')
    implementation 'net.devh:grpc-client-spring-boot-starter:2.12.0.RELEASE' // 추가
    implementation "io.grpc:grpc-protobuf:1.51.0" // 추가하지 않으면 Method not found 에러 발생
    
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

tasks.named('test') {
    useJUnitPlatform()
}
```

#### 3. service에서 stub 함수 사용
```java
package com.example.client;

import custom.CustomRequest;
import custom.CustomResponse;
import custom.CustomServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;

@org.springframework.stereotype.Service
public class Service {

    @GrpcClient("test")
    private CustomServiceGrpc.CustomServiceBlockingStub stub;

    public String send(String name) {
        try {
            CustomResponse customResponse = this.stub.sayHello(
                    CustomRequest.newBuilder()
                            .setName(name)
                            .build()
            );
            return customResponse.getMessage();
        } catch (Exception e) {
            return "ERROR OCCURRED => " + e.getMessage();
        }
    }
}
```