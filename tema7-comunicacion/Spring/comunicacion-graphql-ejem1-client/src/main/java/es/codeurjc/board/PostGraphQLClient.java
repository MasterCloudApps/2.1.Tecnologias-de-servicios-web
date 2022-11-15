package es.codeurjc.board;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Controller;
//
//import graphql.kickstart.spring.webclient.boot.GraphQLRequest;
//import graphql.kickstart.spring.webclient.boot.GraphQLResponse;
//import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;
//import org.springframework.web.reactive.function.client.WebClient;
//
//@Controller
//public class PostGraphQLClient implements CommandLineRunner {
//	private GraphQLWebClient graphqlClient;
//
//	@Override
//	public void run(String... args) throws Exception {
//
//		WebClient webClient = WebClient.builder()
//				.baseUrl("http://127.0.0.1:8080/graphql")//url of graphql instance
//				.build();
//
//		ObjectMapper objectMapper = new ObjectMapper();
//		graphqlClient = GraphQLWebClient.newInstance(webClient, objectMapper);
//
//		getPosts();
//		getPost();
//
//	}
//
//	private void getPosts() {
//
//		System.out.println("Get Posts");
//		System.out.println("---------");
//
//		GraphQLRequest request = GraphQLRequest.builder().query("""
//		query {
//			posts {
//				player
//				title
//				text
//			}
//		}
//		""").build();
//
//		GraphQLResponse response = graphqlClient.post(request).block();
//
//		List<Post> posts = response.getList("posts", Post.class);
//
//		for(Post post : posts) {
//			System.out.println(post);
//		}
//	}
//
//
//	private void getPost() {
//
//		System.out.println("Get Post id:1");
//		System.out.println("-------------");
//
//		GraphQLRequest request = GraphQLRequest.builder().query("""
//		query($id: ID!) {
//		  post(id: $id) {
//		    id
//		  }
//		}
//		""")
//			.variables(Map.of("id",1))
//			.build();
//
//		GraphQLResponse response = graphqlClient.post(request).block();
//
//		Post post = response.get("post", Post.class);
//
//		System.out.println(post);
//	}
//}

import org.springframework.boot.CommandLineRunner;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Controller;

import org.springframework.web.reactive.function.client.WebClient;

@Controller
public class PostGraphQLClient implements CommandLineRunner {
	private HttpGraphQlClient graphqlClient;

	@Override
	public void run(String... args) throws Exception {

		WebClient webClient = WebClient.builder()
				.baseUrl("http://127.0.0.1:8080/graphql")//url of graphql instance
				.build();

		graphqlClient = HttpGraphQlClient.builder(webClient).build();

		getPosts();
		getPost();
	}

	private void getPosts() {

		System.out.println("Get Posts");
		System.out.println("---------");

		String query = """
		query {
			posts {
				username
				title
				text
			}
		}	
		""";

		List<Post> posts = graphqlClient.document(query)
			.retrieve("posts")
			.toEntityList(Post.class).block();

		for(Post post : posts) {
			System.out.println(post);
		}
	}


	private void getPost() {

		System.out.println("Get Post id:1");
		System.out.println("-------------");

		String query = """		
		query($id: ID!) {
		  post(id: $id) {
		    username
		    title
			text
		  }
		}	
		""";

		Post post = graphqlClient.document(query)
				.variables(Map.of("id",1))
				.retrieve("post")
				.toEntity(Post.class).block();

		System.out.println(post);
	}
}