package es.codeurjc;

import es.codeurjc.mastercloudapps.grpc.HelloRequest;
import es.codeurjc.mastercloudapps.grpc.HelloResponse;
import es.codeurjc.mastercloudapps.grpc.HelloServiceGrpc.HelloServiceBlockingStub;
import io.quarkus.grpc.GrpcClient;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class HelloServiceGrpcClient implements QuarkusApplication {

    @GrpcClient("helloServer")
    HelloServiceBlockingStub client;

    @Override
    public int run(String... args) throws Exception {

        HelloRequest request = HelloRequest.newBuilder()
                .setFirstName("Master")
                .setLastName("Cloud Apps")
                .build();

        HelloResponse response = client.hello(request);

        System.out.println("Response received from server:\n" + response);

        return 0;
    }
}
