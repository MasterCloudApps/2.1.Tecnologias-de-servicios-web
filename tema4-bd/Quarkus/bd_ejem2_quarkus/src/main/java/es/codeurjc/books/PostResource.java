package es.codeurjc.board;

import es.codeurjc.books.Post;
import io.smallrye.common.annotation.Blocking;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@Path("/posts")
@Blocking
public class PostResource {

	@GET
	public Collection<Post> getPosts() {
		return Post.listAll();
	}

	@GET
	@Path("{id}")
	public Post getPost(@PathParam("id") long id) {

		Optional<Post> optionalPost = Post.findByIdOptional(id);
		return optionalPost.orElseThrow();
	}

	@POST
	@Transactional
	public Response createPost(Post post, @Context UriInfo uriInfo) {

		Post.persist(post);

		URI location = uriInfo.getAbsolutePathBuilder().path(Long.toString(post.id)).build();

		return Response.created(location).entity(post).build();
	}

	@PUT
	@Path("{id}")
	@Transactional
	public Post replacePost(@PathParam("id") long id, Post newPost) {

		Post.findByIdOptional(id).orElseThrow();

		newPost.id = id;

		Post.getEntityManager().merge(newPost);

		return newPost;
	}

	@DELETE
	@Path("{id}")
	@Transactional
	public Post deletePost(@PathParam("id") long id) {

		Optional<Post> optionalPost = Post.findByIdOptional(id);
		Post post = optionalPost.orElseThrow();

		Post.deleteById(id);

		return post;
	}
}