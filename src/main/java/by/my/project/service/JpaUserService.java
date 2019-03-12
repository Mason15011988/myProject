package by.my.project.service;

import by.my.project.entity.User;
import by.my.project.repository.JpaUserRepository;
import by.my.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JpaUserService implements UserService{

    @Autowired
    private JpaUserRepository userRepository;

    @Override
    public void addUser(User user) {
        userRepository.addUser(user);
    }
}
