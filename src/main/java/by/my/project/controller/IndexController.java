package by.my.project.controller;

import by.my.project.constant.Role;
import by.my.project.entity.Reservation;
import by.my.project.entity.Room;
import by.my.project.entity.Search;
import by.my.project.service.JpaUserService;
import by.my.project.util.FormatDateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
        modelAndView.addObject(NEW_SEARCH, new Search());
        modelAndView.addObject(FLAG_BOOKING, true);
        modelAndView.setViewName(INDEX);
        return modelAndView;
    }

    @PostMapping
    public ModelAndView indexSearch(@RequestParam(CITY) String city, @RequestParam(NUMBER_OF_SEATS) String numberOfSeats,
                                    @RequestParam(START_DATE) Date startDateForm, @RequestParam(END_DATE) Date endDateForm,
                                    ModelAndView modelAndView, HttpServletRequest request) {

        LocalDate localDate = LocalDate.now();
        LocalDate startDate = FormatDateUtil.format(startDateForm);
        LocalDate endDate = FormatDateUtil.format(endDateForm);
        modelAndView.setViewName(INDEX);
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

    @GetMapping(path = HOTEL_SHOW)
    public ModelAndView showHotel(@PathVariable(ID) Integer id, HttpServletRequest request,
                                  ModelAndView modelAndView) {
        modelAndView.setViewName(HOTELS_INDEX);
        List<Room> rooms = (List<Room>) request.getSession().getAttribute(ROOMS_SEARCH);
        Room room = rooms.get(id);
        modelAndView.addObject(ROOM_SEARCH, room);
        return modelAndView;
    }

    @GetMapping(path = EXIT)
    public ModelAndView exit(ModelAndView modelAndView, HttpServletRequest request) {
        request.getSession().setAttribute(ROLE, Role.GUEST);
        modelAndView.setViewName(REDIRECT);
        return modelAndView;
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
