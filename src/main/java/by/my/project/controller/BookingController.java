package by.my.project.controller;

import by.my.project.entity.*;
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
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static by.my.project.constant.Constants.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = USER_SESSION + "/" + USER_PROFILE)
public class BookingController {
    private final JpaUserService userService;

    @GetMapping(path = BOOKING)
    public ModelAndView searchRoom(ModelAndView modelAndView) {
        modelAndView.addObject("newSearch", new Search());
        modelAndView.setViewName(BOOKING);
        return modelAndView;
    }

    @PostMapping(path = BOOKING)
    public ModelAndView searchRoomGetForm(@RequestParam("city") String city, @RequestParam("numberOfSeats") String numberOfSeats,
                                          @RequestParam("startDate") Date startDateForm, @RequestParam("endDate") Date endDateForm,
                                          ModelAndView modelAndView, HttpServletRequest request) {

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
        Search search = getSearch(city, numberOfSeats, startDate, endDate, dates);

        List<Room> roomsBySearchAddress = userService.searchRoomByAddressHotelAndNumberOfSeats(search);
        if (roomsBySearchAddress.size() < 1) {
            modelAndView.addObject("no hotel", "ho hotel");
            modelAndView.setViewName(BOOKING);
            return modelAndView;
        }
        List<Room> roomsBySearchDate = userService.searchRoomByDates(search);
        if (roomsBySearchDate.size() < 1){
            modelAndView.addObject("roomsSearch",roomsBySearchAddress);
            modelAndView.setViewName(BOOKING);
            return modelAndView;
        }
        roomsBySearchAddress.removeAll(roomsBySearchDate);
        if (roomsBySearchAddress.size()<1){
            modelAndView.addObject("no hotel", "ho hotel");
            modelAndView.setViewName(BOOKING);
            return modelAndView;
        }
modelAndView.addObject("no hotel","no hotel");
        modelAndView.setViewName(BOOKING);
        return modelAndView;

//        modelAndView.addObject("roomsAddress", roomsBySearchAddress);
//        modelAndView.addObject("roomsDates", roomsBySearchDate);
//        Room room = roomsBySearchAddress.get(0);
//
//        Reservation reservation = new Reservation();
//        reservation.setStartDate(startDate);
//        reservation.setEndDate(endDate);
//        reservation.setRoom(room);
//        User user = (User) request.getSession().getAttribute(USER_SESSION);
//        user.getReservationList().add(reservation);
//        userService.updateUser(user);
//        Set<Calendar> calendars = new TreeSet<>();
//        for (LocalDate i = startDate; i.compareTo(endDate) <= 0; i = i.plusDays(1)) {
//            Calendar calendar = new Calendar();
//            calendar.setDate(i);
//            calendar.getRoom().add(room);
//            calendars.add(calendar);
//            userService.addCalendar(calendar);
//
//        }
//        room.setCalendar(calendars);
//        userService.updateRoom(room);
//        // userService.addDate(reservation);
    }

    private Search getSearch(@RequestParam("city") String city, @RequestParam("numberOfSeats") String numberOfSeats,
                             LocalDate startDate, LocalDate endDate, Set<LocalDate> dates) {
        Search search = new Search();
        search.setCity(city);
        search.setNumberOfSeats(Integer.valueOf(numberOfSeats));
        search.setStartDate(startDate);
        search.setEndDate(endDate);
        search.setDates(dates);
        return search;
    }
}
