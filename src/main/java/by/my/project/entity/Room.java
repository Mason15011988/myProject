package by.my.project.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Integer id;

    @Column(name = "number_seat")
    @NotEmpty(message = "заполните")
    private Integer numberSeat;

    @Column(name = "price")
    @NotEmpty(message = "заполните")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(cascade = CascadeType.ALL)
    private List<DateOfBooking> dateOfBookingList;
}
