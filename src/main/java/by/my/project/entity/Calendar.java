package by.my.project.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import static by.my.project.constant.Constants.*;

@Data
@Entity
@Table(name = CALENDAR)
@NamedQueries({
        @NamedQuery(name = FIND_CALENDAR, query = "select c from Calendar c where c.id = :id "),
        @NamedQuery(name = FIND_CALENDAR_DATES,
                query = "select c from Calendar c join c.room r where c.date in (:dates) and r.id = :id")})

public class Calendar implements Serializable, Comparable<Calendar> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private Integer id;

    @Column(name = DATE)
    private LocalDate date;

    @ManyToMany(mappedBy = CALENDAR)
    private Set<Room> room = new TreeSet<>();

    @Override
    public int compareTo(Calendar o) {
        return date.compareTo(o.date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calendar calendar = (Calendar) o;
        return Objects.equals(id, calendar.id);
    }
}
