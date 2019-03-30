package by.my.project.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

@Data
public class Search {
    @NotEmpty(message = "error")
    private String country;
    private Integer numberSeat;
    private Date startDate;
    private Date endDate;

}
