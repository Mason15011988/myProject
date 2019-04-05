package by.my.project.service;

import by.my.project.entity.*;

import java.util.List;

public interface UserService {
    User findUserByID(Integer id);

    Reservation findDate(Reservation reservation);

    List<Hotel> searchHotel(Search search);
    List<Room> searchRoomByAddressHotelAndNumberOfSeats(Search search);
    List<Room> searchRoomByDates(Search search);

    void addUser(User user);

    User findUserByEmail(String email);

    User findUser(User user);

    void updateUser(User user);

    void addDate(Reservation reservation);

    void updateRoom(Room room);

    void addCalendar(Calendar calendar);



}
