package es.codeurjc.board;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import graphql.kickstart.spring.webclient.boot.GraphQLRequest;
import graphql.kickstart.spring.webclient.boot.GraphQLResponse;
import graphql.kickstart.spring.webclient.boot.GraphQLWebClient;

@Controller
public class PostGraphQLClient implements CommandLineRunner {

	@Autowired
	private GraphQLWebClient graphQLWebClient;
	
	@Override
	public void run(String... args) throws Exception {
		
		getPosts();
		getPost();
		
	}

	private void getPosts() {
		
		System.out.println("Get Posts");
		System.out.println("---------");
		
		GraphQLRequest request = GraphQLRequest.builder().query("""
		query {
			posts {
				user
				title
				text
			}
		}	
		""").build();
		
		GraphQLResponse response = graphQLWebClient.post(request).block();
	    
		List<Post> posts = response.getList("posts", Post.class);
		
		for(Post post : posts) {
			System.out.println(post);
		}
	}
	
	
	private void getPost() {
		
		System.out.println("Get Post id:1");
		System.out.println("-------------");		
		
		GraphQLRequest request = GraphQLRequest.builder().query("""		
		query($id: ID!) {
		  post(id: $id) {
		    id
		  }
		}	
		""")
			.variables(Map.of("id",1))
			.build();
		
		GraphQLResponse response = graphQLWebClient.post(request).block();
	    
		Post post = response.get("post", Post.class);
		
		System.out.println(post);
	}
}
