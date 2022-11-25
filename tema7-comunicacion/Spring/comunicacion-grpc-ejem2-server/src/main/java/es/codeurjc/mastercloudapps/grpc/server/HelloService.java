package es.codeurjc.mastercloudapps.grpc.server;

import es.codeurjc.mastercloudapps.grpc.HelloRequest;
import es.codeurjc.mastercloudapps.grpc.HelloResponse;
import es.codeurjc.mastercloudapps.grpc.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;

import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HelloService extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void hello(HelloRequest request, 
    		StreamObserver<HelloResponse> responseObserver) {
    	
        System.out.println("Request received from client:\n" + request);

        String greeting = "Hello, " +
                request.getFirstName() +
                " " +
                request.getLastName();

        HelloResponse response = HelloResponse.newBuilder()
            .setGreeting(greeting)
            .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
