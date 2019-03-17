package by.my.project.service;

import by.my.project.entity.User;
import by.my.project.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
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
    public boolean findUserByEmail(String email) {
        try {
            userRepository.findUserByEmail(email);
            return true;
        }catch (NoResultException e){
            return  false;
        }
    }

    @Override
    public void addUser(User user) {
            userRepository.addUser(user);
    }

    @Override
    public User findUser(User user) {
        try {
            return userRepository.findUser(user);
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByID(Integer id) {
        return userRepository.findUserById(id);
    }
}
