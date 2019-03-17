package by.my.project.repository;

import by.my.project.entity.AdminHotel;
import by.my.project.entity.Hotel;

public interface AdminHotelRepository {
     void addAdminHotel(AdminHotel adminHotel);
     AdminHotel findAdminHotelByEmail(String email);
     AdminHotel findAdminHotel(AdminHotel adminHotel);

     void addHotel(Hotel hotel);
}
