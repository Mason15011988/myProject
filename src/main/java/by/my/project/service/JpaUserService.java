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
    public void deleteCalendar(Calendar calendar) {
        userRepository.deleteCalendar(calendar);
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
    public List<Calendar> findDatesFromReservation(Search search) {
        return  userRepository.findDatesFromReservation(search);
    }

    @Override
    public void deleteReservation(Reservation reservation) {
        userRepository.deleteReservation(reservation);
    }

    @Override
    public void addDate(Reservation reservation) {
        userRepository.addDate(reservation);
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
    public List<Room> searchRoomByDates(Search search) {
        try {
            return userRepository.searchRoomByDates(search);
        }catch (NoResultException e){
            return null;
        }

    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByID(Integer id) {
        return userRepository.findUserById(id);
    }

    @Override
    public void addCalendar(Calendar calendar) {
        userRepository.addCalendar(calendar);
    }

    @Override
    public void updateRoom(Room room) {
        userRepository.updateRoom(room);
    }

    @Override
    public List<Room> searchRoomByAddressHotelAndNumberOfSeats(Search search) {
        try {
            return userRepository.searchRoomByAddressHotelAndNumberOfSeats(search);
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public List<Hotel> searchHotel(Search search) {
        try {
            return userRepository.searchHotel(search);
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public Reservation findDate(Reservation reservation) {
        try {
            return userRepository.findDate(reservation);
        } catch (NoResultException e) {
            return null;
        }
    }
}
