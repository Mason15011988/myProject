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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static by.my.project.constant.Constants.*;


@Controller
@RequiredArgsConstructor
@RequestMapping(path = LOGIN)
public class LoginController {

    private final JpaUserService userService;
    private final JpaAdminHotelService adminHotelService;

    @GetMapping
    public ModelAndView login(ModelAndView modelAndView) {
        return ModelAndViewUtil.getModelAndView(modelAndView, GUEST, LOGIN);
    }

    @GetMapping(path = USER)
    public ModelAndView setLoginFormUser(ModelAndView modelAndView) {
        modelAndView.addObject(NEW_USER, new User());
        return ModelAndViewUtil.getModelAndView(modelAndView, USER, LOGIN);
    }

    @PostMapping(path = USER)
    public ModelAndView getLoginFormUser(@Valid @ModelAttribute(NEW_USER) User user, BindingResult bindingResult,
                                         ModelAndView modelAndView, HttpServletRequest request) {
        User userSession;
        user.setRole(Role.USER);
        if (bindingResult.hasErrors()) {
            return ModelAndViewUtil.getModelAndView(modelAndView, USER, LOGIN);
        }
        user.setPassword(PasswordUtil.convertPassToMD5(user.getPassword()));
        if ((userSession = userService.findUser(user)) == null) {
            modelAndView.addObject(MESSAGE_ERROR, MESSAGE_ERROR_FOR_LOGIN);
            return ModelAndViewUtil.getModelAndView(modelAndView, USER, LOGIN);
        }
        request.getSession().setAttribute(USER_SESSION, userSession);
        request.getSession().setAttribute(ROLE, Role.USER);
        modelAndView.setViewName(REDIRECT);
        return modelAndView;
    }

    @GetMapping(path = ADMIN_HOTEL)
    public ModelAndView setLoginFormAdmin(ModelAndView modelAndView) {
        modelAndView.addObject(NEW_ADMIN_HOTEL, new AdminHotel());
        return ModelAndViewUtil.getModelAndView(modelAndView, ADMIN_HOTEL, LOGIN);
    }

    @PostMapping(path = ADMIN_HOTEL)
    public ModelAndView getLoginFormAdmin(@Valid @ModelAttribute(NEW_ADMIN_HOTEL) AdminHotel adminHotel, BindingResult bindingResult,
                                          ModelAndView modelAndView, HttpServletRequest request) {
        AdminHotel adminHotelSession;
        adminHotel.setRole(Role.ADMIN_HOTEL);
        if (bindingResult.hasErrors()) {
            return ModelAndViewUtil.getModelAndView(modelAndView, ADMIN_HOTEL, LOGIN);
        }
        adminHotel.setPassword(PasswordUtil.convertPassToMD5(adminHotel.getPassword()));
        if ((adminHotelSession = adminHotelService.findAdminHotel(adminHotel)) == null) {
            modelAndView.addObject(MESSAGE_ERROR, MESSAGE_ERROR_FOR_LOGIN);
            return ModelAndViewUtil.getModelAndView(modelAndView, ADMIN_HOTEL, LOGIN);
        }
        request.getSession().setAttribute(ADMIN_HOTEL_SESSION, adminHotelSession);
        request.getSession().setAttribute(ROLE, Role.ADMIN_HOTEL);
        modelAndView.setViewName(REDIRECT);
        return modelAndView;
    }
}
