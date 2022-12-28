package com.example.server;

import custom.CustomRequest;
import custom.CustomResponse;
import custom.CustomServiceGrpc.CustomServiceImplBase;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

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
