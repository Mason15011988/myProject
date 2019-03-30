package by.my.project.repository;

import by.my.project.entity.AddressHotel;
import by.my.project.entity.AdminHotel;
import by.my.project.entity.Hotel;
import by.my.project.entity.Room;

public interface AdminHotelRepository {
    void addAdminHotel(AdminHotel adminHotel);

    AdminHotel findAdminHotelByEmail(String email);

    AdminHotel findAdminHotel(AdminHotel adminHotel);

    void addHotel(Hotel hotel);

    void addRoom(Room room);

    void addHotelAddress(AddressHotel addressHotel);

    void updateAdminHotel(AdminHotel adminHotel);

    void updateHotel(Hotel hotel);

}
