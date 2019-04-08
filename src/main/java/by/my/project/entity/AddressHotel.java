package by.my.project.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static by.my.project.constant.Constants.*;

@Data
@Embeddable
public class AddressHotel implements Serializable {

    @Column(name = COUNTRY)
    @NotEmpty(message = NO_COUNTRY)
    private String country;

    @Column(name = CITY)
    @NotEmpty(message = NO_CITY)
    private String city;

    @Column(name = STREET)
    @NotEmpty(message = NO_STREET)
    private String street;

    @Column(name = HOUSE)
    @NotNull(message = NO_HOUSE)
    private Integer house;
}
