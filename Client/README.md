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

#### 4. application.properties
- grpc.client.{name}
```properties
grpc.client.test.address=static://localhost:9090
grpc.client.test.negotiation-type=plaintext
```
negotiation-type은 기본 TLS로 설정되어있으며 여기서는 간단한 예제 용도이므로 plaintext를 사용했다. plaintext의 경우 TLS를 사용하지 않는다는 의미이다.

#### 5. server, client 기동 후 결과 확인
![스크린샷 2022-12-29 오후 2 51 23](https://user-images.githubusercontent.com/58351498/209909717-b44a503d-15fb-4c80-9fea-d939efd71ae7.png)

server와 client의 내부적인 통신 프로토콜이 gRPC이고 사용자가 http://localhost:8080의 리소스를 접근하는 것은 HTTP이다. 
따라서 브라우저로는 gRPC인지 확인할 수 없다.