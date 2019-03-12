package by.my.project.entity;

import by.my.project.constant.Role;
import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @Size(min = 1, message = "Имя отсутствует")
    private String name;

    @Email
    @Column(name = "email")
    @Size(min = 1, message = "Email отсутствует")
    private String email;

    @Column(name = "password")
    @Size(min = 1, message = "Пароль отсутствует")
    private String password;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "role")
    private Role role;
}
