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
