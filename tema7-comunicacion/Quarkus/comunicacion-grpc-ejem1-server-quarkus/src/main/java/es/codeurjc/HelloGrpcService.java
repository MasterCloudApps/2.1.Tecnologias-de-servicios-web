package es.codeurjc;

import io.quarkus.grpc.GrpcService;

import io.smallrye.mutiny.Uni;

import es.codeurjc.mastercloudapps.grpc.*;

@GrpcService
public class HelloGrpcService implements HelloService {

    @Override
    public Uni<HelloResponse> hello(HelloRequest request) {

        System.out.println("Request received from client:\n" + request);

        String greeting = "Hello, " +
                request.getFirstName() +
                " " +
                request.getLastName();

        HelloResponse response = HelloResponse.newBuilder()
                .setGreeting(greeting)
                .build();

        return Uni.createFrom().item(response);
    }
}
