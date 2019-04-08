package by.my.project.repository;

import by.my.project.entity.*;

import java.util.List;

public interface UserRepository {

    void addUser(User user);

    User findUserByEmail(String email);

    User findUser(User user);

    void updateUser(User user);

    List<Room> findRoomByAddressHotelAndNumberOfSeats(Search search);

    List<Room> findRoomByDates(Search search);

    void updateRoom(Room room);

    void deleteReservation(Reservation reservation);

    Reservation findReservation(Reservation reservation);

    List<Calendar> findDatesFromReservation(Search search);

    void addCalendar(Calendar calendar);

    Calendar findCalendar(Calendar calendar);

    void deleteCalendar(Calendar calendar);
}
