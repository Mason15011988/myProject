package by.my.project.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static by.my.project.constant.Constants.*;

@Data
@Entity
@Table(name = HOTEL)
@NamedQueries({@NamedQuery(name = FIND_HOTEL, query = "select h from Hotel h where h.id = :id ")})

public class Hotel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private Integer id;

    @Column(name = NAME_HOTEL)
    @NotEmpty(message = NO_NAME_HOTEL)
    private String name;

    @Column(name = STARS)
    @NotNull(message = NO_STARS)
    private Integer stars;

    @Column(name = DESCRIPTION)
    @NotEmpty(message = NO_DESCRIPTION)
    private String description;

    @Embedded
    private AddressHotel addressHotel;

    @ManyToOne
    private AdminHotel adminHotel;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = HOTEL)
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
