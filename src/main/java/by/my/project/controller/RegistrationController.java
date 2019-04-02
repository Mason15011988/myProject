package by.my.project.controller;

import by.my.project.constant.Role;
import by.my.project.entity.AdminHotel;
import by.my.project.entity.User;
import by.my.project.service.JpaAdminHotelService;
import by.my.project.service.JpaUserService;
import by.my.project.util.ModelAndViewUtil;
import by.my.project.util.PasswordUtil;
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
@RequestMapping(path = REGISTRATION)
public class RegistrationController {

    private final JpaUserService userService;
    private final JpaAdminHotelService adminHotelService;

    @GetMapping
    public ModelAndView view(ModelAndView modelAndView) {
        return ModelAndViewUtil.getModelAndView(modelAndView, GUEST, REGISTRATION);
    }

    @GetMapping(path = USER)
    public ModelAndView setRegistrationFormUser(ModelAndView modelAndView) {
        modelAndView.addObject(NEW_USER, new User());
        return ModelAndViewUtil.getModelAndView(modelAndView, USER, REGISTRATION);
    }

    @PostMapping(path = USER)
    public ModelAndView getRegistrationFormUser(@Valid @ModelAttribute(NEW_USER) User user, BindingResult bindingResult,
                                                ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            return ModelAndViewUtil.getModelAndView(modelAndView, USER, REGISTRATION);
        }
        if (userService.findUserByEmail(user.getEmail()) != null) {
            modelAndView.addObject(MESSAGE_ERROR, MESSAGE_ERROR_FOR_USER_BY_EMAIL);
            return ModelAndViewUtil.getModelAndView(modelAndView, USER, REGISTRATION);
        }
        user.setPassword(PasswordUtil.convertPassToMD5(user.getPassword()));
        user.setRole(Role.USER);
        userService.addUser(user);
        modelAndView.setViewName(REDIRECT);
        return modelAndView;
    }

    @GetMapping(path = ADMIN_HOTEL)
    public ModelAndView setRegistrationFormAdmin(ModelAndView modelAndView) {
        modelAndView.addObject(NEW_ADMIN_HOTEL, new AdminHotel());
        return ModelAndViewUtil.getModelAndView(modelAndView, ADMIN_HOTEL, REGISTRATION);
    }

    @PostMapping(path = ADMIN_HOTEL)
    public ModelAndView getRegistrationFormAdmin(@Valid @ModelAttribute(NEW_ADMIN_HOTEL) AdminHotel adminHotel,
                                                 BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            return ModelAndViewUtil.getModelAndView(modelAndView, ADMIN_HOTEL, REGISTRATION);
        }
        if (adminHotelService.findAdminHotelByEmail(adminHotel.getEmail()) != null) {
            modelAndView.addObject(MESSAGE_ERROR, MESSAGE_ERROR_FOR_ADMIN_BY_EMAIL);
            return ModelAndViewUtil.getModelAndView(modelAndView, ADMIN_HOTEL, REGISTRATION);
        }
        adminHotel.setRole(Role.ADMIN_HOTEL);
        adminHotel.setPassword(PasswordUtil.convertPassToMD5(adminHotel.getPassword()));
        adminHotelService.addAdminHotel(adminHotel);
        modelAndView.setViewName(REDIRECT);
        return modelAndView;
    }
}
