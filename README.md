# gRPC-spring-boot
gRPC 프로토콜을 사용한 간단한 spring boot application

#### 🖋️ 짧은 회고?
gRPC는 HTTP/2 기반으로 protocol buffer를 사용하여 데이터를 바이트 스트림으로 직렬화하여 통신한다. 따라서 json으로 데이터를 주고받을 때보다 통신 속도가 더 빠르다. 이러한 장점덕에 MSA에서 마이크로서비스들끼리 통신할때 사용된다고 한다. 내부적으로 마이크로서비스끼리 통신이 빈번하기 때문이다. 또한 네트워크를 사용하는 구조이므로 지연율을 낮추는 것도 중요하다.

더 나아가, gRPC은 통신 규약이 엄격하다. 그말인 즉슨, `.proto` 파일로 생성된 request와 response를 message를 정해서 사용한다는 것이다. API 문서를 기반으로 한 경험을 되돌아보면, 개발하면서 자주 변경되었지만 문서에는 반영이 잘 안되었던 것 같다. 그래서 gRPC를 사용하게 된다면 이러한 불편함은 줄어들고 신뢰성이 올라갈 것이라고 생각한다. 하지만 PB와 gRPC를 위한 명세는 따로 고민해봐야 될 것이다. 최근에 완료한 사용자 인증 프로젝트부터 gRPC로 변경해보면서 고민한 내용들을 정리해봐야겠다.

#### 🖇️ reference
[yidongnan/grpc-spring-boot-starter](https://github.com/yidongnan/grpc-spring-boot-starter)

[google develpers](https://developers.google.com/protocol-buffers/docs/overview)

[내 블로그](https://velog.io/@mardi2020/gRPC)
