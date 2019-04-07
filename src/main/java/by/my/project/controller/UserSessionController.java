package by.my.project.controller;

import by.my.project.entity.*;
import by.my.project.service.JpaUserService;
import by.my.project.util.ModelAndViewUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static by.my.project.constant.Constants.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = USER_SESSION)
public class UserSessionController {
    private final JpaUserService userService;

    @GetMapping
    public ModelAndView userSession(ModelAndView modelAndView, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(USER_SESSION);
        return ModelAndViewUtil.getModelAndView(modelAndView, USER, SESSION);
    }

    @GetMapping(path = USER_PROFILE)
    public ModelAndView userProfile(ModelAndView modelAndView, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(USER_SESSION);
        modelAndView.addObject(USER_SESSION, user);
        return ModelAndViewUtil.getModelAndView(modelAndView, USER, PROFILE);
    }

    @GetMapping(path = USER_PROFILE + "/" + RESERVATIONS)
    public ModelAndView userReservation(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName(RESERVATIONS);
        User userSession = (User) request.getSession().getAttribute(USER_SESSION);
        User user = userService.findUser(userSession);
        request.getSession().setAttribute(USER_SESSION,user);
        List<Reservation> reservations = user.getReservationList();
        if (reservations.size() < 1) {
            modelAndView.addObject(MESSAGE_ERROR, NO_RESERVATION);
            return modelAndView;
        }
        modelAndView.addObject(RESERVATIONS, reservations);
        return modelAndView;
    }

    @GetMapping(path = USER_PROFILE + "/" + RESERVATION_DELETE)
    public ModelAndView userDeleteReservation(@PathVariable(ID) Integer id, ModelAndView modelAndView,
                                              HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(USER_SESSION);
        List<Reservation> reservations = user.getReservationList();
        Reservation reservationForDelete = reservations.get(id);
        Set<LocalDate> dates = new TreeSet<>();
        for (LocalDate i = reservationForDelete.getStartDate(); i.compareTo(reservationForDelete.getEndDate()) <= 0; i = i.plusDays(1)) {
            dates.add(i);
        }
        Search search = new Search();
        search.setDates(dates);
        search.setIdRoom(reservationForDelete.getRoom().getId());
        List<Calendar> calendars = userService.findDatesFromReservation(search);
        Room room = reservationForDelete.getRoom();
        room.getCalendar().removeAll(calendars);
        userService.updateRoom(room);
        for (Calendar calendar:calendars) {
            userService.deleteCalendar(calendar);
        }
        userService.deleteReservation(reservationForDelete);
        modelAndView.setViewName(REDIRECT + USER_SESSION);
        return modelAndView;

    }
}
