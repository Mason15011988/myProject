package by.my.project.entity;

import by.my.project.constant.Role;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import static by.my.project.constant.Constants.*;

@Data
@Entity
@Table(name = ADMIN_HOTEL_BD)
@NamedQueries({
        @NamedQuery(name = FIND_ADMIN_HOTEL,
                query = "select a from AdminHotel a where a.email = :email and a.password = :password"),
        @NamedQuery(name = FIND_ADMIN_HOTEL_BY_EMAIL,
                query = "select a from  AdminHotel a where a.email =:email")})

public class AdminHotel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private Integer id;

    @Email
    @Column(name = EMAIL)
    @NotEmpty(message = NO_EMAIL)
    private String email;

    @Column(name = PASSWORD)
    @NotEmpty(message = NO_PASSWORD)
    private String password;

    @Column(name = DATE)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = ROLE)
    private Role role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = ADMIN_HOTEL)
    private List<Hotel> hotelList;

    @PrePersist
    void prePersist() {
        date = new Date();
    }

    @Override
    public String toString() {
        return "AdminHotel{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", date=" + date +
                ", role=" + role +
                '}';
    }
}
