package es.mastercloudapps.security_example.service;

import es.mastercloudapps.security_example.model.Role;
import es.mastercloudapps.security_example.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String rolename);
    User getUser(String username);
    List<User> getUsers();
}
