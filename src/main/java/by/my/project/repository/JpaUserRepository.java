package by.my.project.repository;

import by.my.project.entity.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaUserRepository implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findUserById(Integer id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findUser(User user) {
        return entityManager.createNamedQuery("User.findUser", user.getClass()).
                setParameter("email", user.getEmail()).
                setParameter("password", user.getPassword()).
                getSingleResult();
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public List<Room> searchRoomByDates(Search search) {

        String sql = " select r from Room r join r.calendar c where c.date in (?1) ";
        return (List<Room>) entityManager.createQuery(sql).
                setParameter(1, search.getDates()).
                getResultList();
    }

    @Override
    public List<Room> searchRoomByAddressHotelAndNumberOfSeats(Search search) {
        String sql = " select r from Room r join r.hotel h where h.addressHotel.city = ?1 and r.numberOfSeats = ?2 ";
        return (List<Room>) entityManager.createQuery(sql).
                setParameter(1, search.getCity()).
                setParameter(2, search.getNumberOfSeats()).
                getResultList();

    }

    @Override
    public List<Hotel> searchHotel(Search search) {
        String sql = " select h from Hotel h join h.roomList r where h.addressHotel.city = ?1 and r.numberOfSeats = ?2 ";
        return (List<Hotel>) entityManager.createQuery(sql).
                setParameter(1, search.getCity()).
                setParameter(2, search.getNumberOfSeats()).
                getResultList();

    }

    @Override
    public void addCalendar(Calendar calendar) {
        entityManager.persist(calendar);
    }

    @Override
    public void updateRoom(Room room) {
        entityManager.merge(room);
    }

    @Override
    public void addDate(Reservation reservation) {
        entityManager.persist(reservation);
    }

    @Override
    public User findUserByEmail(String email) {
        return entityManager.createNamedQuery("User.findUserByEmail", User.class).
                setParameter("email", email).
                getSingleResult();
    }

    @Override
    public Reservation findDate(Reservation reservation) {
        String sql = "from Reservation where id = ?1";
        return (Reservation) entityManager.createQuery(sql).
                setParameter(1, reservation.getId()).
                getSingleResult();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }
}
