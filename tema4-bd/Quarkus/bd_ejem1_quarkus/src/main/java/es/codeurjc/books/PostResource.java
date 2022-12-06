package es.codeurjc.board;

import es.codeurjc.books.Post;
import es.codeurjc.books.PostRepository;
import io.smallrye.common.annotation.Blocking;

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
	public Collection<Post> getPosts(@QueryParam("username") String username) {

		if(username == null){
			return posts.listAll();
		} else {
			return posts.findByUsername(username);
		}
	}

	@GET
	@Path("{id}")
	public Post getPost(@PathParam("id") long id) {

		return posts.findByIdOptional(id).orElseThrow();
	}

	@POST
	@Transactional
	public Response createPost(Post post, @Context UriInfo uriInfo) {

		posts.persist(post);

		URI location = uriInfo.getAbsolutePathBuilder().path(Long.toString(post.getId())).build();

		return Response.created(location).entity(post).build();
	}

	@PUT
	@Path("{id}")
	@Transactional
	public Post replacePost(@PathParam("id") long id, Post newPost) {

		posts.findByIdOptional(id).orElseThrow();

		newPost.setId(id);

		posts.getEntityManager().merge(newPost);

		return newPost;
	}

	@DELETE
	@Path("{id}")
	@Transactional
	public Post deletePost(@PathParam("id") long id) {

		Post post = posts.findByIdOptional(id).orElseThrow();

		posts.deleteById(id);

		return post;
	}
}