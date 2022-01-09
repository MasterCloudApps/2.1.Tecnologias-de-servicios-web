package es.mastercloudapps.security_example.controller;

import es.mastercloudapps.security_example.model.Role;
import es.mastercloudapps.security_example.model.User;
import es.mastercloudapps.security_example.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.Servlet;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/enroll")
    public ResponseEntity<Role> addRoleToUser(@RequestBody RoleToUserForm roleToUserForm){
        userService.addRoleToUser(roleToUserForm.getUsername(), roleToUserForm.getRolename());
        return ResponseEntity.ok().build();
    }
}

@Data
class RoleToUserForm{
    private String username;
    private String rolename;
}