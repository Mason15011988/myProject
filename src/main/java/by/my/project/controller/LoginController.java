package by.my.project.controller;

import by.my.project.constant.Role;
import by.my.project.entity.AdminHotel;
import by.my.project.entity.User;
import by.my.project.service.JpaAdminHotelService;
import by.my.project.service.JpaUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static by.my.project.constant.Constants.*;


@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/" + LOGIN)
public class LoginController {

    private final JpaUserService userService;
    private final JpaAdminHotelService adminHotelService;

    @GetMapping
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName(LOGIN);
        return modelAndView;
    }

    @GetMapping(path = "/" + USER)
    public ModelAndView setLoginFormUser(ModelAndView modelAndView) {
        modelAndView.setViewName(LOGIN_USER);
        modelAndView.addObject(NEW_USER, new User());
        return modelAndView;
    }

    @PostMapping(path = "/" + USER)
    public ModelAndView getLoginFormUser(@Valid @ModelAttribute(NEW_USER) User user, BindingResult bindingResult,
                                         ModelAndView modelAndView, HttpServletRequest httpServletRequest) {
        User userSession;
        user.setRole(Role.USER);
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(LOGIN_USER);
            return modelAndView;
        }
        if ((userSession = userService.findUser(user)) == null) {
            modelAndView.setViewName(LOGIN_USER);
            modelAndView.addObject(MESSAGE_ERROR, MESSAGE_ERROR_FOR_LOGIN);
            return modelAndView;
        }
        httpServletRequest.getSession().setAttribute(USER_SESSION, userSession);
        httpServletRequest.getSession().setAttribute(ROLE, Role.USER);
        modelAndView.setViewName(REDIRECT);
        return modelAndView;
    }

    @GetMapping(path = "/" + ADMIN_HOTEL)
    public ModelAndView setLoginFormAdmin(ModelAndView modelAndView) {
        modelAndView.setViewName(LOGIN_ADMIN);
        modelAndView.addObject(NEW_ADMIN, new AdminHotel());
        return modelAndView;
    }

    @PostMapping(path = "/" + ADMIN_HOTEL)
    public ModelAndView getLoginFormUser(@Valid @ModelAttribute(NEW_ADMIN) AdminHotel adminHotel, BindingResult bindingResult,
                                         ModelAndView modelAndView, HttpServletRequest httpServletRequest) {
        AdminHotel adminHotelSession;
        adminHotel.setRole(Role.ADMIN_HOTEL);
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(LOGIN_ADMIN);
            return modelAndView;
        }
        if ((adminHotelSession = adminHotelService.findAdminHotel(adminHotel)) == null) {
            modelAndView.setViewName(LOGIN_ADMIN);
            modelAndView.addObject(MESSAGE_ERROR, MESSAGE_ERROR_FOR_LOGIN);
            return modelAndView;
        }
        httpServletRequest.getSession().setAttribute(ADMIN_SESSION, adminHotelSession);
        httpServletRequest.getSession().setAttribute(ROLE, Role.ADMIN_HOTEL);
        modelAndView.setViewName(REDIRECT);
        return modelAndView;
    }
}
