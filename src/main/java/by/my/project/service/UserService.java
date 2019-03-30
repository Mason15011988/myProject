package by.my.project.service;

import by.my.project.entity.DateOfBooking;
import by.my.project.entity.User;

public interface UserService {
    User findUserByID(Integer id);

    void addUser(User user);

    User findUserByEmail(String email);

    User findUser(User user);

    void updateUser(User user);

    void addDate(DateOfBooking dateOfBooking);


}
