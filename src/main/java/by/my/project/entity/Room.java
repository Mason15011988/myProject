package by.my.project.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Data
@Entity
@Table(name = "room")
public class Room implements Serializable, Cloneable ,Comparable{

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "number_of_seats")
    @NotNull(message = "заполните")
    private Integer numberOfSeats;

    @Column(name = "number_room")
    @NotNull(message = "заполните")
    private Integer numberRoom;

    @Column(name = "price")
    @NotNull(message = "заполните")
    private Integer price;


    //    @Transient
//    private Integer count;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "calendar_room",
            joinColumns = @JoinColumn(name = "room_id"),
            inverseJoinColumns = @JoinColumn(name = "calendar_id"))
    private Set<Calendar> calendar = new TreeSet<>();

    @ManyToOne
    private Hotel hotel;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room")
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
//    @Override
//    public Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }

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
