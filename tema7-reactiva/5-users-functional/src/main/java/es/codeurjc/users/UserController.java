package es.codeurjc.users;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
