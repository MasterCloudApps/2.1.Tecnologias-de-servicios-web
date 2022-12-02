package es.codeurjc.board;

import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;
@Mapper(componentModel = "spring")
public interface PostMapper {

    PostDTO toDTO(Post post);

    List<PostDTO> toDTOs(Collection<Post> posts);

    Post toDomain(PostDTO postDTO);
}
