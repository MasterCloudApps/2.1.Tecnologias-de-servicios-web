package es.codeurjc.mastercloudapps.grpc.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import es.codeurjc.mastercloudapps.grpc.HelloRequest;
import es.codeurjc.mastercloudapps.grpc.HelloResponse;
import es.codeurjc.mastercloudapps.grpc.HelloServiceGrpc.HelloServiceBlockingStub;
import net.devh.boot.grpc.client.inject.GrpcClient;

@Controller
public class HelloServiceGrpcClient implements CommandLineRunner {

	@GrpcClient("helloServer")
	private HelloServiceBlockingStub client;
	
	@Override
	public void run(String... args) throws Exception {
		
		HelloRequest request = HelloRequest.newBuilder()
	            .setFirstName("Master")
	            .setLastName("Cloud Apps")
	            .build();
	        
		HelloResponse response = client.hello(request);

	    System.out.println("Response received from server:\n" + response);
		
	}	
}
