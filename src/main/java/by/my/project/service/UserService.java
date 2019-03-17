package by.my.project.service;

import by.my.project.entity.User;

public interface UserService {
    User findUserByID(Integer id);
    void addUser(User user);
    boolean findUserByEmail(String email);
    User findUser(User user);
}
