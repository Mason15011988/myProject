package by.my.project.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

import static by.my.project.constant.Constants.*;

@Data
@Entity
@Table(name = RESERVATION)
@NamedQueries({@NamedQuery(name = FIND_RESERVATION, query = "select r from Reservation r where r.id = :id ")})

public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID)
    private Integer id;

    @Column(name = START_DATE_BD)
    private LocalDate startDate;

    @Column(name = END_DATE_BD)
    private LocalDate endDate;

    @Column(name = PRICE)
    private Integer price;

    @ManyToOne
    @JoinColumn(name = ROOM_ID)
    private Room room;
}
