package by.my.project.service;

import by.my.project.entity.AdminHotel;

public interface AdminHotelService {
    void addAdminHotel(AdminHotel adminHotel);
    boolean findAdminHotelByEmail(String email);
    AdminHotel findAdminHotel(AdminHotel adminHotel);
}
