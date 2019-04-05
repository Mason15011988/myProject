package by.my.project.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Data
@Entity
@Table(name = "calendar")
public class Calendar implements Serializable,Comparable<Calendar> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date")
    private LocalDate date;

    @ManyToMany(mappedBy = "calendar")
    private Set<Room> room = new TreeSet<>();


    @Override
    public int compareTo(Calendar o) {
        return date.compareTo(o.date);
    }
}
