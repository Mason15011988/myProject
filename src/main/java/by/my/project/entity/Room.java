package by.my.project.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "rooms")
public class Room implements Serializable,Cloneable {

    @Id
    @GeneratedValue
    @Column(name = "room_id")
    private Integer id;

    @Column(name = "number_seat")
    @NotNull(message = "заполните")
    private Integer numberSeat;

    @Column(name = "number_room")
    @NotNull(message = "заполните")
    private Integer numberRoom;

    @Column(name = "price")
    @NotNull(message = "заполните")
    private Integer price;

//    @Transient
//    private Integer count;

    @ManyToOne
    private Hotel hotel;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "room")
    private List<DateOfBooking> dateOfBookingList;

//    @Override
//    public Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", numberSeat=" + numberSeat +
                ", numberRoom=" + numberRoom +
                ", price=" + price +
                ", hotel=" + hotel +
                '}';
    }
}
