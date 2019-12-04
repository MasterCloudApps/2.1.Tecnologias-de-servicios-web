package es.codeurjc.mastercloudapps.grpc.client;

import es.codeurjc.mastercloudapps.grpc.HelloRequest;
import es.codeurjc.mastercloudapps.grpc.HelloResponse;
import es.codeurjc.mastercloudapps.grpc.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {
    
	public static void main(String[] args) throws InterruptedException {
        
    	ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
            .usePlaintext()
            .build();

        HelloServiceGrpc.HelloServiceBlockingStub client = 
        		HelloServiceGrpc.newBlockingStub(channel);

        HelloRequest request = HelloRequest.newBuilder()
            .setFirstName("Baeldung")
            .setLastName("gRPC")
            .build();
        
		HelloResponse response = client.hello(request);

        System.out.println("Response received from server:\n" + response);

        channel.shutdown();
    }
}
