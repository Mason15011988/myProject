package by.my.project.repository;

import by.my.project.entity.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static by.my.project.constant.Constants.*;

@Repository
public class JpaUserRepository implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findUser(User user) {
        return entityManager.createNamedQuery(FIND_USER, user.getClass()).
                setParameter(EMAIL, user.getEmail()).
                setParameter(PASSWORD, user.getPassword()).
                getSingleResult();
    }

    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return entityManager.createNamedQuery(FIND_USER_BY_EMAIL, User.class).
                setParameter(EMAIL, email).
                getSingleResult();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<Room> findRoomByDates(Search search) {
        return (List<Room>) entityManager.createNamedQuery(FIND_ROOM_BY_DATES).
                setParameter(DATES, search.getDates()).
                getResultList();
    }

    @Override
    public List<Room> findRoomByAddressHotelAndNumberOfSeats(Search search) {
        return (List<Room>) entityManager.createNamedQuery(FIND_ROOM_BY_ADDRESS).
                setParameter(HOTEL_ADDRESS, search.getCity()).
                setParameter(NUMBER_OF_SEATS, search.getNumberOfSeats()).
                getResultList();
    }

    @Override
    public void updateRoom(Room room) {
        entityManager.merge(room);
    }

    @Override
    public List<Calendar> findDatesFromReservation(Search search) {
        return (List<Calendar>) entityManager.createNamedQuery(FIND_CALENDAR_DATES).
                setParameter(DATES, search.getDates()).
                setParameter(ID, search.getIdRoom()).
                getResultList();
    }

    @Override
    public Calendar findCalendar(Calendar calendar) {
        return entityManager.createNamedQuery(FIND_CALENDAR, Calendar.class).
                setParameter(ID, calendar.getId()).
                getSingleResult();

    }

    @Override
    public void deleteCalendar(Calendar calendar) {
        Calendar calendarForDel = findCalendar(calendar);
        entityManager.remove(entityManager.contains(calendarForDel) ? calendarForDel : entityManager.merge(calendarForDel));
    }

    @Override
    public void addCalendar(Calendar calendar) {
        entityManager.persist(calendar);
    }

    @Override
    public void deleteReservation(Reservation reservation) {
        Reservation reservationForDel = findReservation(reservation);
        entityManager.remove(entityManager.contains(reservationForDel) ? reservationForDel : entityManager.merge(reservationForDel));
    }

    @Override
    public Reservation findReservation(Reservation reservation) {
        return entityManager.createNamedQuery(FIND_RESERVATION, Reservation.class).
                setParameter(ID, reservation.getId()).
                getSingleResult();
    }
}
