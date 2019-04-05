package by.my.project.controller;

import by.my.project.constant.Role;
import by.my.project.entity.Reservation;
import by.my.project.entity.Room;
import by.my.project.entity.Search;
import by.my.project.service.JpaUserService;
import by.my.project.util.FormatDateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static by.my.project.constant.Constants.*;

@RequiredArgsConstructor
@Controller
@RequestMapping(path = "/")
public class IndexController {
    private final JpaUserService userService;

    @GetMapping
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.addObject("newSearch", new Search());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView indexSearch(@RequestParam("city") String city, @RequestParam("numberOfSeats") String numberOfSeats,
                                    @RequestParam("startDate") Date startDateForm, @RequestParam("endDate") Date endDateForm,
                                    ModelAndView modelAndView) {

        LocalDate localDate = LocalDate.now();
        LocalDate startDate = FormatDateUtil.format(startDateForm);
        LocalDate endDate = FormatDateUtil.format(endDateForm);
        if (startDate.compareTo(localDate) < 0) {
            modelAndView.addObject("errorNowDate", "дата должна быть больше или равна текущей ");
            modelAndView.setViewName("index");
            return modelAndView;
        }
        if (endDate.compareTo(startDate) < 0) {
            modelAndView.addObject("errorDate", "дата отьезда должна быть больше даты заезда");
            modelAndView.setViewName("index");
            return modelAndView;
        }

        Set<LocalDate> dates = new TreeSet<>();
        for (LocalDate i = startDate; i.compareTo(endDate) <= 0; i = i.plusDays(1)) {
            dates.add(i);
        }
        Search search = new Search();
        search.setCity(city);
        search.setNumberOfSeats(Integer.valueOf(numberOfSeats));
        search.setStartDate(startDate);
        search.setEndDate(endDate);
        search.setDates(dates);

        Reservation reservation = new Reservation();
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        userService.addDate(reservation);
        List<Room> roomsBySearchAddress = userService.searchRoomByAddressHotelAndNumberOfSeats(search);
        List<Room> roomsBySearchDate = userService.searchRoomByDates(search);


        modelAndView.addObject("roomsAddress", roomsBySearchAddress);
        modelAndView.addObject("roomsDates", roomsBySearchDate);

        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping(path = EXIT)
    public ModelAndView exit(ModelAndView modelAndView, HttpServletRequest request) {
        request.getSession().setAttribute(ROLE, Role.GUEST);
        modelAndView.setViewName(REDIRECT);
        return modelAndView;
    }
}
