package by.my.project.service;

import by.my.project.entity.AddressHotel;
import by.my.project.entity.AdminHotel;
import by.my.project.entity.Hotel;
import by.my.project.entity.Room;

public interface AdminHotelService {

    void addAdminHotel(AdminHotel adminHotel);

    void updateAdminHotel(AdminHotel adminHotel);

    AdminHotel findAdminHotel(AdminHotel adminHotel);

    AdminHotel findAdminHotelByEmail(String email);

    void updateHotel(Hotel hotel);

    void addHotel(Hotel hotel);

    void deleteHotel(Hotel hotel);

    void deleteRoom(Room room);

    void updateRoom(Room room);

    Room findNumberRoom(Room room);

}
