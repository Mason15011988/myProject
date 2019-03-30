package by.my.project.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Embeddable
public class AddressHotel {

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
    @NotNull(message = "Дом отсутствует")
    private Integer house;
}
