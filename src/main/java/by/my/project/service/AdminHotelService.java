package by.my.project.service;

import by.my.project.entity.AddressHotel;
import by.my.project.entity.AdminHotel;
import by.my.project.entity.Hotel;
import by.my.project.entity.Room;

public interface AdminHotelService {
    void addAdminHotel(AdminHotel adminHotel);

    void updateAdminHotel(AdminHotel adminHotel);

    void updateHotel(Hotel hotel);

    AdminHotel findAdminHotelByEmail(String email);

    AdminHotel findAdminHotel(AdminHotel adminHotel);

    void addHotel(Hotel hotel);

    void addRoom(Room room);

    void addHotelAddress(AddressHotel addressHotel);
}
