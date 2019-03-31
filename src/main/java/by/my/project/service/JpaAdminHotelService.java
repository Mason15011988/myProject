package by.my.project.service;

import by.my.project.entity.AddressHotel;
import by.my.project.entity.AdminHotel;
import by.my.project.entity.Hotel;
import by.my.project.entity.Room;
import by.my.project.repository.JpaAdminHotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;

@Service
@Transactional
@RequiredArgsConstructor
public class JpaAdminHotelService implements AdminHotelService {

    @Override
    public void addRoom(Room room) {
        adminHotelRepository.addRoom(room);
    }

    private final JpaAdminHotelRepository adminHotelRepository;

    @Override
    public void addAdminHotel(AdminHotel adminHotel) {
        adminHotelRepository.addAdminHotel(adminHotel);
    }

    @Override
    public Hotel findHotel(Hotel hotel) {
        try {
            return adminHotelRepository.findHotel(hotel);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void deleteHotel(Hotel hotel) {
        adminHotelRepository.deleteHotel(hotel);
    }

    @Override
    public void updateHotel(Hotel hotel) {
        adminHotelRepository.updateHotel(hotel);
    }

    @Override
    public void updateAdminHotel(AdminHotel adminHotel) {
        adminHotelRepository.updateAdminHotel(adminHotel);
    }

    @Override
    public AdminHotel findAdminHotel(AdminHotel adminHotel) {
        try {
            return adminHotelRepository.findAdminHotel(adminHotel);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void addHotelAddress(AddressHotel addressHotel) {
        adminHotelRepository.addHotelAddress(addressHotel);
    }

    @Override
    public void addHotel(Hotel hotel) {
        adminHotelRepository.addHotel(hotel);
    }

    @Override
    @Transactional(readOnly = true)
    public AdminHotel findAdminHotelByEmail(String email) {
        try {
            return adminHotelRepository.findAdminHotelByEmail(email);

        } catch (NoResultException e) {
            return null;
        }
    }
}
