package by.my.project.entity;

import by.my.project.constant.Role;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "admin_hotel")
public class AdminHotel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Email
    @Column(name = "email")
    @NotEmpty(message = "Email отсутствует")
    private String email;

    @Column(name = "password")
    @NotEmpty(message = "Пароль отсутствует")
    private String password;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "role")
    private Role role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adminHotel")
//    @JoinColumn(name = "admin_hotel_id")
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
