package by.my.project.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import static by.my.project.constant.Constants.*;

@Data
@Entity
@Table(name = ROOM)
@NamedQueries({
        @NamedQuery(name = FIND_NUMBER_ROOM,
                query = "select r from Room r join r.hotel h where r.numberRoom = :numberRoom and h.id = :id"),
        @NamedQuery(name = FIND_ROOM, query = "select r from Room r where r.id = :id "),
        @NamedQuery(name = FIND_ROOM_BY_DATES,
                query = "select r from Room r join r.calendar c where c.date in (:dates)"),
        @NamedQuery(name = FIND_ROOM_BY_ADDRESS,
                query = "select r from Room r join r.hotel h" +
                        " where lower(h.addressHotel.city)  like lower(concat('%',:hotelAddress,'%') )" +
                        " and r.numberOfSeats = :numberOfSeats")})

public class Room implements Serializable, Comparable {

    @Id
    @GeneratedValue
    @Column(name = ID)
    private Integer id;

    @Column(name = NUMBER_OF_SEATS_BD)
    @NotNull(message = FILL)
    private Integer numberOfSeats;

    @Column(name = ROOM_NUMBER)
    @NotNull(message = FILL)
    private Integer numberRoom;

    @Column(name = PRICE)
    @NotNull(message = FILL)
    private Integer price;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = CALENDAR_ROOM,
            joinColumns = @JoinColumn(name = ROOM_ID),
            inverseJoinColumns = @JoinColumn(name = CALENDAR_ID))
    private Set<Calendar> calendar = new TreeSet<>();

    @ManyToOne
    private Hotel hotel;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = ROOM)
    private List<Reservation> reservationList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(id, room.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", numberOfSeats=" + numberOfSeats +
                ", numberRoom=" + numberRoom +
                ", price=" + price +
                ", hotel=" + hotel +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
