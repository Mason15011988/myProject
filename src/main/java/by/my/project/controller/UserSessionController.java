package by.my.project.controller;

import by.my.project.constant.Role;
import by.my.project.entity.AdminHotel;
import by.my.project.entity.Reservation;
import by.my.project.entity.User;
import by.my.project.service.JpaUserService;
import by.my.project.util.ModelAndViewUtil;
import by.my.project.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static by.my.project.constant.Constants.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = USER_SESSION)
public class UserSessionController {
    private final JpaUserService userService;
    @GetMapping
    public ModelAndView userSession(ModelAndView modelAndView,HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(USER_SESSION);
        return ModelAndViewUtil.getModelAndView(modelAndView, USER, SESSION);
    }

    @GetMapping(path = USER_PROFILE)
    public ModelAndView userProfile(ModelAndView modelAndView, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(USER_SESSION);
        modelAndView.addObject(USER_SESSION, user);
        return ModelAndViewUtil.getModelAndView(modelAndView, USER, PROFILE);
    }
    @GetMapping(path = USER_PROFILE+"/"+RESERVATIONS)
    public ModelAndView userReservation(ModelAndView modelAndView, HttpServletRequest request) {
        User userSession = (User) request.getSession().getAttribute(USER_SESSION);
        User user = userService.findUser(userSession);
        List<Reservation> reservations = user.getReservationList();
        if (reservations.size()<1){
            modelAndView.addObject(MESSAGE_ERROR, NO_RESERVATION);
            modelAndView.addObject(RESERVATIONS, reservations);
            modelAndView.setViewName(RESERVATIONS);
            return modelAndView;
        }
        modelAndView.addObject(USER_SESSION, user);
        return ModelAndViewUtil.getModelAndView(modelAndView, USER, PROFILE);
    }
    @GetMapping(path = USER_PROFILE+"/"+RESERVATION_DELETE)
    public ModelAndView userDeleteReservation(@PathVariable(ID) Integer id,ModelAndView modelAndView,
                                              HttpServletRequest request) {



    }
}
