package by.my.project.controller;

import by.my.project.entity.*;
import by.my.project.service.JpaUserService;
import by.my.project.util.FormatDateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping(path = USER_SESSION)
public class BookingController {
    private final JpaUserService userService;

    @GetMapping(path = BOOKING)
    public ModelAndView searchRoom(ModelAndView modelAndView) {
        modelAndView.addObject(NEW_SEARCH, new Search());
        modelAndView.addObject(FLAG_BOOKING, true);
        modelAndView.setViewName(BOOKING);
        return modelAndView;
    }

    @PostMapping(path = BOOKING)
    public ModelAndView searchRoomGetForm(@RequestParam(CITY) String city, @RequestParam(NUMBER_OF_SEATS) String numberOfSeats,
                                          @RequestParam(START_DATE) Date startDateForm, @RequestParam(END_DATE) Date endDateForm,
                                          ModelAndView modelAndView, HttpServletRequest request) {

        modelAndView.setViewName(BOOKING);
        LocalDate localDate = LocalDate.now();
        LocalDate startDate = FormatDateUtil.format(startDateForm);
        LocalDate endDate = FormatDateUtil.format(endDateForm);
        if (startDate.compareTo(localDate) < 0) {
            modelAndView.addObject(MESSAGE_ERROR, MESSAGE_ERROR_FOR_DATE);
            return modelAndView;
        }
        if (endDate.compareTo(startDate) < 0) {
            modelAndView.addObject(MESSAGE_ERROR, MESSAGE_ERROR_FOR_DATE_END);
            return modelAndView;
        }

        Set<LocalDate> dates = new TreeSet<>();
        for (LocalDate i = startDate; i.compareTo(endDate) <= 0; i = i.plusDays(1)) {
            dates.add(i);
        }
        Integer days = dates.size();
        modelAndView.addObject(DAYS, days);
        request.getSession().setAttribute(DAYS,days);
        modelAndView.addObject(FLAG_BOOKING, false);
        Search search = getSearch(city, numberOfSeats, startDate, endDate, dates);
        request.getSession().setAttribute(NEW_SEARCH, search);
        List<Room> roomsBySearchAddress = userService.searchRoomByAddressHotelAndNumberOfSeats(search);
        if (roomsBySearchAddress.size() < 1) {
            modelAndView.addObject(MESSAGE_ERROR_NO_HOTEL, NO_HOTEL);
            return modelAndView;
        }
        List<Room> roomsBySearchDate = userService.searchRoomByDates(search);
        if (roomsBySearchDate.size() < 1) {
            request.getSession().setAttribute(ROOMS_SEARCH, roomsBySearchAddress);
            modelAndView.addObject(ROOMS_SEARCH, roomsBySearchAddress);
            return modelAndView;
        }
        roomsBySearchAddress.removeAll(roomsBySearchDate);
        if (roomsBySearchAddress.size() < 1) {
            modelAndView.addObject(MESSAGE_ERROR_NO_HOTEL, NO_HOTEL);
            return modelAndView;
        }
        request.getSession().setAttribute(ROOMS_SEARCH, roomsBySearchAddress);
        modelAndView.addObject(ROOMS_SEARCH, roomsBySearchAddress);
        return modelAndView;
    }

    @GetMapping(path = BOOKING + "/" + HOTEL_SHOW)
    public ModelAndView showHotel(@PathVariable(ID) Integer id, HttpServletRequest request,
                                  ModelAndView modelAndView) {
        List<Room> rooms = (List<Room>) request.getSession().getAttribute(ROOMS_SEARCH);
        Room room = rooms.get(id);
        modelAndView.addObject(ROOM_SEARCH, room);
        modelAndView.setViewName(HOTELS_FOM_SEARCH);
        return modelAndView;
    }

    @GetMapping(path = BOOKING + "/" + RESERVATION_ROOM)
    public ModelAndView reservationRoom(@PathVariable(ID) Integer id, HttpServletRequest request,
                                        ModelAndView modelAndView) {
        List<Room> rooms = (List<Room>) request.getSession().getAttribute(ROOMS_SEARCH);
        Room roomForReservation = rooms.get(id);
        Search searchForReservation = (Search) request.getSession().getAttribute(NEW_SEARCH);
        Integer days = (Integer) request.getSession().getAttribute(DAYS);

        Reservation reservation = getReservation(roomForReservation, searchForReservation, days);
        User user = (User) request.getSession().getAttribute(USER_SESSION);
        User userForReservation = userService.findUser(user);
        userForReservation.getReservationList().add(reservation);
        userService.updateUser(userForReservation);
        Set<Calendar> calendars = new TreeSet<>();
        for (LocalDate i = searchForReservation.getStartDate(); i.compareTo(searchForReservation.getEndDate()) <= 0; i = i.plusDays(1)) {
            Calendar calendar = new Calendar();
            calendar.setDate(i);
            calendar.getRoom().add(roomForReservation);
            calendars.add(calendar);
            userService.addCalendar(calendar);
        }
        roomForReservation.setCalendar(calendars);
        userService.updateRoom(roomForReservation);
        modelAndView.setViewName(REDIRECT + USER_SESSION);
        return modelAndView;
    }

    private Reservation getReservation(Room roomForReservation, Search searchForReservation, Integer days) {
        Reservation reservation = new Reservation();
        reservation.setStartDate(searchForReservation.getStartDate());
        reservation.setEndDate(searchForReservation.getEndDate());
        reservation.setRoom(roomForReservation);
        reservation.setPrice(days * roomForReservation.getPrice());
        return reservation;
    }

    private Search getSearch(@RequestParam(CITY) String city, @RequestParam(NUMBER_OF_SEATS) String numberOfSeats,
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
