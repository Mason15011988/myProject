package by.my.project.util;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormatDateUtil {
    public static LocalDate format(Date date) {
        DateTimeFormatter dateTimeFormat =
                DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dateTimeFormat.parse(date.toString(), LocalDate::from);

    }
}
