package by.my.project.repository;

import by.my.project.entity.AddressHotel;
import by.my.project.entity.AdminHotel;
import by.my.project.entity.Hotel;
import by.my.project.entity.Room;
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
    public void updateAdminHotel(AdminHotel adminHotel) {
        entityManager.merge(adminHotel);
    }

    @Override
    public AdminHotel findAdminHotelByEmail(String email) {
        String sql = "from AdminHotel where email = ?1";

        return (AdminHotel) entityManager.createQuery(sql).
                setParameter(1, email).
                getSingleResult();
    }

    @Override
    public AdminHotel findAdminHotel(AdminHotel adminHotel) {
        String sql = "from AdminHotel where email = ?1 and password =?2";
        return (AdminHotel) entityManager.createQuery(sql).
                setParameter(1, adminHotel.getEmail()).
                setParameter(2, adminHotel.getPassword()).
                getSingleResult();
    }

    @Override
    public void updateHotel(Hotel hotel) {
        entityManager.merge(hotel);
    }

    @Override
    public Hotel findHotel(Hotel hotel) {
        String sql = "from Hotel where id = ?1";
        return (Hotel) entityManager.createQuery(sql).
                setParameter(1, hotel.getId()).
                getSingleResult();
    }

    @Override
    public void addHotel(Hotel hotel) {
        entityManager.persist(hotel);
    }

    @Override
    public void deleteHotel(Hotel hotel) {
        Hotel hotelForDel = findHotel(hotel);
        entityManager.remove(entityManager.contains(hotelForDel) ? hotelForDel : entityManager.merge(hotelForDel));
    }

    @Override
    public Room findRoom(Room room) {
        String sql = "from Room where id = ?1";
        return (Room) entityManager.createQuery(sql).
                setParameter(1, room.getId()).
                getSingleResult();
    }

    @Override
    public Room findNumberRoom(Room room) {
        String sql = "from Room where numberRoom = ?1";
        return (Room) entityManager.createQuery(sql).
                setParameter(1, room.getNumberRoom()).
                getSingleResult();
    }

    @Override
    public void deleteRoom(Room room) {
        Room roomForDel = findRoom(room);
        entityManager.remove(entityManager.contains(roomForDel) ? roomForDel : entityManager.merge(roomForDel));
    }

    @Override
    public void updateRoom(Room room) {
        entityManager.merge(room);
    }
}
