package es.codeurjc.board;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Collection;

@Path("/posts")
public class PostResource {

	@Inject
	PostService posts;

	@GET
	public Collection<Post> getPosts() {
		return posts.findAll();
	}

	@GET
	@Path("{id}")
	public Response getPost(@PathParam("id") long id) {

		Post post = posts.findById(id);

		if (post != null) {
			return Response.ok(post).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@POST
	public Response createPost(Post post, @Context UriInfo uriInfo) {

		posts.save(post);

		URI location = uriInfo.getAbsolutePathBuilder().path(Long.toString(post.getId())).build();

		return Response.created(location).entity(post).build();
	}

	@PUT
	@Path("{id}")
	public Response replacePost(@PathParam("id") long id, Post newPost) {

		Post post = posts.findById(id);

		if (post != null) {

			newPost.setId(id);
			posts.save(newPost);

			return Response.ok(newPost).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@DELETE
	@Path("{id}")
	public Response deletePost(@PathParam("id") long id) {

		Post post = posts.findById(id);

		if (post != null) {
			posts.deleteById(id);
			return Response.ok(post).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
}
