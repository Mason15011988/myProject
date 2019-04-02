package by.my.project.repository;

import by.my.project.entity.AddressHotel;
import by.my.project.entity.AdminHotel;
import by.my.project.entity.Hotel;
import by.my.project.entity.Room;

public interface AdminHotelRepository {
    void addAdminHotel(AdminHotel adminHotel);

    AdminHotel findAdminHotelByEmail(String email);

    AdminHotel findAdminHotel(AdminHotel adminHotel);

    void updateAdminHotel(AdminHotel adminHotel);

    void addHotel(Hotel hotel);

    Hotel findHotel(Hotel hotel);

    void deleteHotel(Hotel hotel);

    void updateHotel(Hotel hotel);

    Room findRoom(Room room);

    Room findNumberRoom(Room room);

    void deleteRoom(Room room);

    void updateRoom(Room room);

}
