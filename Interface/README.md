### IDL(Interface Definition Language)

#### 1.  `.proto` 파일
src/main/proto 에 사용할 proto file을 작성한다. 

- CustomRequest를 가져와 CustomResponse를 반환하는 RPC 서비스와 메시지
```protobuf
syntax = "proto3"; 

package custom;
option java_multiple_files = true;

service CustomService {
  rpc SayHello (CustomRequest) returns (CustomResponse) {
  }
}

message CustomRequest {
  string name = 1;
}

message CustomResponse {
  string message = 1;
}
```

- syntax = "proto3": proto3 구문 사용
- package: 고유한 프로젝트 이름 설정
- java_multiple_files
  - true: 최상위 수준인 클래스, enum에 해당하는 자바 클래스, enum 파일, 서비스에 관한 자바 클래스의 enum 클래스, 서비스 등은 자바 클래스/enum 등 각각에 대해 별도의 .java 파일이 생성

그 외 자세한 내용은 https://developers.google.com/protocol-buffers/docs/proto3 참조

#### 2. PB 컴파일러(protoc) 사용
`.proto`에 정의된 service, message를 인터페이스 코드와 스텁을 자바 언어로 생성한다.

#### 3. jar 파일을 client, server에 포함시킨다.
인터페이스에 정의된 메서드 상세 구현은 server에서 구현한다.