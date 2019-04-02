package by.my.project.controller;

import by.my.project.constant.Role;
import by.my.project.entity.AdminHotel;
import by.my.project.entity.User;
import by.my.project.service.JpaUserService;
import by.my.project.util.ModelAndViewUtil;
import by.my.project.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static by.my.project.constant.Constants.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = USER_SESSION)
public class UserSessionController {
    private final JpaUserService userService;
    @GetMapping
    public ModelAndView userSession(ModelAndView modelAndView) {
        return ModelAndViewUtil.getModelAndView(modelAndView, USER, SESSION);
    }

    @GetMapping(path = USER_PROFILE)
    public ModelAndView adminProfile(ModelAndView modelAndView, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(USER_SESSION);
        modelAndView.addObject(USER_SESSION, user);
        return ModelAndViewUtil.getModelAndView(modelAndView, USER, PROFILE);
    }

}
