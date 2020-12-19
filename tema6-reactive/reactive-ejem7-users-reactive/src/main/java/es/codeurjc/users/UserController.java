package es.codeurjc.users;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/users/")
public class UserController {

    private UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UserDTO> createUser(@RequestBody Mono<UserDTO> user) {
        return user
            .map(this::toUser)
            .flatMap(userService::createUser)
            .map(this::toUserDTO);
    }

    @GetMapping("/")
    public Flux<UserDTO> getUsers(@RequestParam(required = false) Optional<String> firstName) {
        return userService.getUsers(firstName)
            .map(this::toUserDTO);
    }

    @GetMapping("/{id}")
    public Mono<UserDTO> getUser(@PathVariable Long id) {
        return userService.getUser(id)
            .map(this::toUserDTO);
    }

    @DeleteMapping("/{id}")
    public Mono<UserDTO> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id).map(this::toUserDTO);
    }

    private User toUser(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getFirstName(), userDTO.getLastName());
    }

    private UserDTO toUserDTO(User user) {
        return new UserDTO(user.getId(), user.getFirstName(), user.getLastName());
    }

}
