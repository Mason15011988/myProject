package by.my.project.service;

import by.my.project.entity.DateOfBooking;
import by.my.project.entity.User;
import by.my.project.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;

@Service
@Transactional
@RequiredArgsConstructor
public class JpaUserService implements UserService {

    private final JpaUserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public User findUserByEmail(String email) {
        try {
            return userRepository.findUserByEmail(email);

        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void addDate(DateOfBooking dateOfBooking) {
        userRepository.addDate(dateOfBooking);
    }

    @Override
    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    @Override
    public void addUser(User user) {
        userRepository.addUser(user);
    }

    @Override
    public User findUser(User user) {
        try {
            return userRepository.findUser(user);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByID(Integer id) {
        return userRepository.findUserById(id);
    }
}
