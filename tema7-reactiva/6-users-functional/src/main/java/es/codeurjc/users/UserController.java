package es.codeurjc.users;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

@RestController
@RequestMapping("/users/")
public class UserController {

    private UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody UserDTO user) {
        return toUserDTO(userService.createUser(toUser(user)));
    }

    @GetMapping("/")
    public Stream<UserDTO> getUsers(@RequestParam(required = false) Optional<String> firstName) {
        return userService.getUsers(firstName)
            .map(this::toUserDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        return ResponseEntity.of(userService.getUser(id).map(this::toUserDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long id) {

        Optional<UserDTO> userDTO = userService.deleteUser(id)
            .map(this::toUserDTO);

        return ResponseEntity.of(userDTO);
    }

    private User toUser(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getFirstName(), userDTO.getLastName());
    }

    private UserDTO toUserDTO(User user) {
        return new UserDTO(user.getId(), user.getFirstName(), user.getLastName());
    }

}
