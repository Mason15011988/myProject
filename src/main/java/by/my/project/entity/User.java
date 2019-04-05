package by.my.project.entity;

import by.my.project.constant.Role;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "user")
@NamedQueries(
        {@NamedQuery(name = "User.findUser",
        query = "select u from User u where u.email = :email and u.password = :password"),
        @NamedQuery(name = "User.findUserByEmail",query = "select u from  User u where u.email =:email")
        })
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Email
    @Column(name = "email", unique = true)
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "date_id")
    private List<Reservation> reservationList = new ArrayList<>();

    @PrePersist
    void prePersist() {
        date = new Date();
    }
}
