package by.my.project.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Data
@Entity
@Table(name = "addresses")
public class AddressHotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer id;

    @Column(name = "country")
    @NotEmpty(message = "Страна отсутствует")
    private String country;

    @Column(name = "city")
    @NotEmpty(message = "Город отсутствует")
    private String city;

    @Column(name = "street")
    @NotEmpty(message = "Улица отсутствует")
    private String street;

    @Column(name = "house")
    @NotEmpty(message = "Дом отсутствует")
    private Integer house;

    @OneToOne(mappedBy = "addressHotel")
    private Hotel hotel;
}
