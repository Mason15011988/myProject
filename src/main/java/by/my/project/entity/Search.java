package by.my.project.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class Search {
    private String city;
    private Integer numberOfSeats;
    private LocalDate startDate;
    private LocalDate endDate;
    private Set<LocalDate> dates;
    private Integer idRoom;
}
