package by.my.project.service;

import by.my.project.entity.*;
import by.my.project.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class JpaUserService implements UserService {

    private final JpaUserRepository userRepository;

    @Override
    public void addUser(User user) {
        userRepository.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUser(User user) {
        try {
            return userRepository.findUser(user);
        } catch (NoResultException e) {
            return null;
        }
    }

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
    @Transactional(readOnly = true)
    public List<Room> findRoomByDates(Search search) {
        try {
            return userRepository.findRoomByDates(search);
        }catch (NoResultException e){
            return null;
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<Room> findRoomByAddressHotelAndNumberOfSeats(Search search) {
        try {
            return userRepository.findRoomByAddressHotelAndNumberOfSeats(search);
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void updateRoom(Room room) {
        userRepository.updateRoom(room);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Calendar> findDatesFromReservation(Search search) {
        return  userRepository.findDatesFromReservation(search);
    }

    @Override
    public void deleteCalendar(Calendar calendar) {
        userRepository.deleteCalendar(calendar);
    }

    @Override
    public void addCalendar(Calendar calendar) {
        userRepository.addCalendar(calendar);
    }

    @Override
    public void deleteReservation(Reservation reservation) {
        userRepository.deleteReservation(reservation);
    }
}
