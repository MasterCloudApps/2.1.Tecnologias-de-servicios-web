package es.codeurjc.board;

import io.smallrye.common.annotation.Blocking;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Collection;

@Path("/posts")
@Blocking
public class PostResource {

	@Inject
	PostRepository posts;

	@GET
	public Collection<Post> getPosts() {
		return posts.listAll();
	}

	@GET
	@Path("{id}")
	public Post getPost(@PathParam("id") String id) {

		return posts.findByIdOptional(new ObjectId(id)).orElseThrow();
	}

	@POST
	@Transactional
	public Response createPost(Post post, @Context UriInfo uriInfo) {

		posts.persist(post);

		URI location = uriInfo.getAbsolutePathBuilder().path(post.getId().toString()).build();

		return Response.created(location).entity(post).build();
	}

	@PUT
	@Path("{id}")
	@Transactional
	public Post replacePost(@PathParam("id") String id, Post newPost) {

		posts.findByIdOptional(new ObjectId(id)).orElseThrow();

		newPost.setId(new ObjectId(id));

		posts.update(newPost);

		return newPost;
	}

	@DELETE
	@Path("{id}")
	@Transactional
	public Post deletePost(@PathParam("id") String id) {

		Post post = posts.findByIdOptional(new ObjectId(id)).orElseThrow();

		posts.deleteById(new ObjectId(id));

		return post;
	}
}