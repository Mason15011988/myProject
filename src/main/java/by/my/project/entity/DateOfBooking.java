package by.my.project.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "dates")
public class DateOfBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "date_id")
    private Integer id;

    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
   // @NotEmpty(message = "заполните")
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
   // @NotEmpty(message = "заполните")
    private Date endDate;

    @ManyToOne
    //@JoinColumn(name = "room_id")
    private Room room;

//    @ManyToOne
//    @JoinColumn(name="user_id")
//    private User user;
}
