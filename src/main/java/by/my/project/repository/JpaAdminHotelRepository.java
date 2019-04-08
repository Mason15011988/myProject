package by.my.project.repository;

import by.my.project.entity.AdminHotel;
import by.my.project.entity.Hotel;
import by.my.project.entity.Room;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static by.my.project.constant.Constants.*;

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
        return entityManager.createNamedQuery(FIND_ADMIN_HOTEL_BY_EMAIL, AdminHotel.class).
                setParameter(EMAIL, email).
                getSingleResult();
    }

    @Override
    public AdminHotel findAdminHotel(AdminHotel adminHotel) {
        return entityManager.createNamedQuery(FIND_ADMIN_HOTEL, AdminHotel.class).
                setParameter(EMAIL, adminHotel.getEmail()).
                setParameter(PASSWORD, adminHotel.getPassword()).
                getSingleResult();
    }

    @Override
    public void updateHotel(Hotel hotel) {
        entityManager.merge(hotel);
    }

    @Override
    public Hotel findHotel(Hotel hotel) {
        return entityManager.createNamedQuery(FIND_HOTEL, Hotel.class).
                setParameter(ID, hotel.getId()).
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
        return entityManager.createNamedQuery(FIND_ROOM, Room.class).
                setParameter(ID, room.getId()).
                getSingleResult();
    }

    @Override
    public Room findNumberRoom(Room room) {
        return entityManager.createNamedQuery(FIND_NUMBER_ROOM, Room.class).
                setParameter(NUMBER_ROOM, room.getNumberRoom()).
                setParameter(ID, room.getHotel().getId()).
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
