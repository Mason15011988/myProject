package by.my.project.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "hotels")
public class Hotel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private Integer id;

    @Column(name = "name_hotel")
    @NotEmpty(message = "Название отеля отсутствует")
    private String name;

    @Column(name = "stars")
    private Integer stars;

    @Column(name="description")
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "hotel_address_id")
    private AddressHotel addressHotel;

    @ManyToOne
    @JoinColumn(name = "admin_hotel_id")
    private AdminHotel adminHotel;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Room> roomList;
}
