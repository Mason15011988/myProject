package by.my.project.repository;

import by.my.project.entity.DateOfBooking;
import by.my.project.entity.User;

public interface UserRepository {
    void addUser(User user);

    User findUserByEmail(String email);

    User findUserById(Integer id);

    User findUser(User user);

    void updateUser(User user);

    void addDate(DateOfBooking dateOfBooking);
}
