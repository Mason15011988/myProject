package by.my.project.repository;

import by.my.project.entity.*;

import java.util.List;
import java.util.Set;

public interface UserRepository {
    void addUser(User user);

    User findUserByEmail(String email);

    Reservation findDate(Reservation reservation);

    User findUserById(Integer id);

    User findUser(User user);

    void updateUser(User user);

    void addDate(Reservation reservation);

    List<Hotel> searchHotel(Search search);

    List<Room> searchRoomByAddressHotelAndNumberOfSeats(Search search);

    List<Room> searchRoomByDates(Search search);

    void updateRoom(Room room);

    void addCalendar(Calendar calendar);

    void deleteReservation(Reservation reservation);

    Reservation findReservation(Reservation reservation);

    List<Calendar> findDatesFromReservation(Search search);

    Calendar findCalendar (Calendar calendar);

    void deleteCalendar(Calendar calendar);
}
