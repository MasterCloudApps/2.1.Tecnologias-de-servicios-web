package es.codeurjc.books;

import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.common.annotation.NonBlocking;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Collection;
import java.util.List;

@Path("/posts")
public class PostResource {

	@Inject
	PostRepository posts;

	@GET
	public Uni<List<Post>> getPosts(@QueryParam("username") String username) {

		if(username == null){
			return posts.listAll();
		} else {
			return posts.findByUsername(username);
		}
	}

	@GET
	@Path("{id}")
	public Uni<Response> getPost(@PathParam("id") long id) {
		return posts.findById(id)
				.onItem().transform(
						post -> post == null?
								Response.status(Response.Status.NOT_FOUND).build() :
								Response.ok(post).build());
	}

	@POST
	@ReactiveTransactional
	public Uni<Response> createPost(Post post, @Context UriInfo uriInfo) {

		return posts.persist(post).onItem().transform( p ->
			Response.created(getLocation(uriInfo, p)).entity(p).build()
		);
	}

	private static URI getLocation(UriInfo uriInfo, Post p) {
		return uriInfo.getAbsolutePathBuilder().path(Long.toString(p.getId())).build();
	}

	@PUT
	@Path("{id}")
	@ReactiveTransactional
	public Uni<Response> replacePost(@PathParam("id") long id, Post newPost) {

		newPost.setId(id);

		return posts.findById(id)
				.onItem().ifNull().failWith(new NotFoundException())
				.onItem().transformToUni(p -> posts.getSession())
				.onItem().transformToUni(s -> s.merge(newPost))
				.onItem().transform(p -> Response.ok(p).build())
				.onFailure().recoverWithItem(() -> Response.status(Response.Status.NOT_FOUND).build());
	}

	@DELETE
	@Path("{id}")
	@ReactiveTransactional
	public Uni<Response> deletePost(@PathParam("id") long id) {

		return posts.findById(id)
				.onItem().ifNull().failWith(new NotFoundException())
				.onItem().transformToUni(p -> posts.deleteById(id).onItem().transform(deleted -> p))
				.onItem().transform(p -> Response.ok(p).build())
				.onFailure().recoverWithItem(() -> Response.status(Response.Status.NOT_FOUND).build());
	}
}
