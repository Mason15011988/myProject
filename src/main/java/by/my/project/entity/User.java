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

import static by.my.project.constant.Constants.*;


@Data
@Entity
@Table(name = USER)
@NamedQueries({
        @NamedQuery(name = FIND_USER,
            query = "select u from User u where u.email = :email and u.password = :password"),
        @NamedQuery(name = FIND_USER_BY_EMAIL,query = "select u from  User u where u.email =:email")})

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private Long id;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = USER_ID)
    private List<Reservation> reservationList = new ArrayList<>();

    @PrePersist
    void prePersist() {
        date = new Date();
    }
}
