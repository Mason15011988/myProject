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

import javax.validation.Valid;

import static by.my.project.constant.Constants.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/" + REGISTRATION)
public class RegistrationController {

    private final JpaUserService userService;
    private final JpaAdminHotelService adminHotelService;

    @GetMapping
    public ModelAndView view(ModelAndView modelAndView) {
        modelAndView.setViewName(REGISTRATION);
        return modelAndView;
    }

    @GetMapping(path = "/" + USER)
    public ModelAndView setRegistrationFormUser(ModelAndView modelAndView) {
        modelAndView.setViewName(REGISTRATION_USER);
        modelAndView.addObject(NEW_USER, new User());
        return modelAndView;
    }

    @PostMapping(path = "/" + USER)
    public ModelAndView getRegistrationFormUser(@Valid @ModelAttribute(NEW_USER) User user, BindingResult bindingResult,
                                                ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(REGISTRATION_USER);
            return modelAndView;
        }
        if (userService.findUserByEmail(user.getEmail())) {
            modelAndView.addObject(MESSAGE_ERROR, MESSAGE_ERROR_FOR_USER_BY_EMAIL);
            modelAndView.setViewName(REGISTRATION_USER);
            return modelAndView;
        }
        user.setRole(Role.USER);
        userService.addUser(user);
        modelAndView.setViewName(REDIRECT);
        return modelAndView;

    }

    @GetMapping(path = "/" + ADMIN_HOTEL)
    public ModelAndView setRegistrationFormAdmin(ModelAndView modelAndView) {
        modelAndView.setViewName(REGISTRATION_ADMIN);
        modelAndView.addObject(NEW_ADMIN, new AdminHotel());
        return modelAndView;
    }

    @PostMapping(path = "/" + ADMIN_HOTEL)
    public ModelAndView getRegistrationFormAdmin(@Valid @ModelAttribute(NEW_ADMIN) AdminHotel adminHotel,
                                                 BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(REGISTRATION_ADMIN);
            return modelAndView;
        }
        if (adminHotelService.findAdminHotelByEmail(adminHotel.getEmail())){
            modelAndView.addObject(MESSAGE_ERROR, MESSAGE_ERROR_FOR_ADMIN_BY_EMAIL);
            modelAndView.setViewName(REGISTRATION_ADMIN);
            return modelAndView;
        }
        adminHotel.setRole(Role.ADMIN_HOTEL);
        adminHotelService.addAdminHotel(adminHotel);
        modelAndView.setViewName(REDIRECT);
        return modelAndView;
    }
}
