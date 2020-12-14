package es.codeurjc.users;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public List<UserDTO> getUsers(@RequestParam(required = false) String firstName) {
        List<UserDTO> usersDTO = new ArrayList<>();
        for(User user : userService.getUsers(firstName)){
            usersDTO.add(toUserDTO(user));
        }
        return usersDTO;
    }

    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        return toUserDTO(userService.getUser(id));
    }

    @DeleteMapping("/{id}")
    public UserDTO deleteUser(@PathVariable Long id) {
        return toUserDTO(userService.deleteUser(id));
    }

    private User toUser(UserDTO userDTO){
        return new User(userDTO.getId(), userDTO.getFirstName(), userDTO.getLastName());
    }

    private UserDTO toUserDTO(User user){
        return new UserDTO(user.getId(), user.getFirstName(), user.getLastName());
    }

}
