package com.yesmine.games.service;

import com.yesmine.games.model.Role;
import com.yesmine.games.model.User;

public interface UserService {
    void deleteAllusers();
    void deleteAllRoles();
    User saveUser(User user);
    User findUserByUsername (String username);
    Role addRole(Role role);
    User addRoleToUser(String username, String rolename);
}
