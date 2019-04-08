package by.my.project.service;

import by.my.project.entity.*;

import java.util.List;

public interface UserService {

    void addUser(User user);

    User findUserByEmail(String email);

    User findUser(User user);

    void updateUser(User user);

    void updateRoom(Room room);

    List<Room> findRoomByAddressHotelAndNumberOfSeats(Search search);

    List<Room> findRoomByDates(Search search);

    void addCalendar(Calendar calendar);

    List<Calendar> findDatesFromReservation(Search search);

    void deleteCalendar(Calendar calendar);

    void deleteReservation(Reservation reservation);

}
