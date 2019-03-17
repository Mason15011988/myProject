package by.my.project.service;

import by.my.project.entity.AdminHotel;
import by.my.project.repository.JpaAdminHotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;

@Service
@Transactional
@RequiredArgsConstructor
public class JpaAdminHotelService implements AdminHotelService {

    private final JpaAdminHotelRepository adminHotelRepository;

    @Override
    public void addAdminHotel(AdminHotel adminHotel) {
        adminHotelRepository.addAdminHotel(adminHotel);
    }

    @Override
    public AdminHotel findAdminHotel(AdminHotel adminHotel) {
        try {
            return adminHotelRepository.findAdminHotel(adminHotel);
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean findAdminHotelByEmail(String email) {
        try {
            adminHotelRepository.findAdminHotelByEmail(email);
            return true;
        }catch (NoResultException e){
            return  false;
        }
    }
}
