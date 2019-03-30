package by.my.project.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Column(name = "description")
    private String description;

    @Embedded
    private AddressHotel addressHotel;

    @ManyToOne
    // @JoinTable(name = "admins_hotel_hotels")
    private AdminHotel adminHotel;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "hotel")
    private List<Room> roomList = new ArrayList<>();

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stars=" + stars +
                ", description='" + description + '\'' +
                ", addressHotel=" + addressHotel +
                ", adminHotel=" + adminHotel +
                '}';
    }
}
