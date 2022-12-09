package es.codeurjc.board;

import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Id;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Collection;

@GraphQLApi
public class PostResource {

	@Inject
	PostRepository posts;

	@Query
	public Collection<Post> posts() {
		return posts.listAll();
	}

	@Query
	public Post post(@Id long id) {
		return posts.findByIdOptional(id).orElseThrow();
	}

	@Mutation
	@Transactional
	public Post createPost(Post post) {

		posts.persist(post);

		return post;
	}

	@Mutation
	@Transactional
	public Post replacePost(@Id long id, Post newPost) {

		posts.findByIdOptional(id).orElseThrow();

		newPost.setId(id);

		posts.getEntityManager().merge(newPost);

		return newPost;
	}

	@Mutation
	@Transactional
	public Post deletePost(@Id long id) {

		Post post = posts.findByIdOptional(id).orElseThrow();

		posts.deleteById(id);

		return post;
	}
}