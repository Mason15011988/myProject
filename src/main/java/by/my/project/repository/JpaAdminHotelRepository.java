package by.my.project.repository;

import by.my.project.entity.AdminHotel;
import by.my.project.entity.Hotel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class JpaAdminHotelRepository implements AdminHotelRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void addAdminHotel(AdminHotel adminHotel) {
        entityManager.persist(adminHotel);
    }

    @Override
    public AdminHotel findAdminHotel(AdminHotel adminHotel) {
        String sql = "from AdminHotel where email = ?1 and password =?2";
        return (AdminHotel) entityManager.createQuery(sql).
                setParameter(1,adminHotel.getEmail()).
                setParameter(2,adminHotel.getPassword()).
                getSingleResult();
    }

    @Override
    public AdminHotel findAdminHotelByEmail(String email) {
        String sql = "from AdminHotel where email = ?1";

        return (AdminHotel) entityManager.createQuery(sql).
                setParameter(1,email).
                getSingleResult();
    }

    @Override
    public void addHotel(Hotel hotel) {
        entityManager.persist(hotel);
    }
}
