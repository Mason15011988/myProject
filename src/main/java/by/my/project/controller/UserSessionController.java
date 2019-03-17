package by.my.project.controller;

import by.my.project.service.JpaUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static by.my.project.constant.Constants.USER_SESSION;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/"+ USER_SESSION)
public class UserSessionController {
    private final JpaUserService userService;
    @GetMapping
    public ModelAndView userSession(ModelAndView modelAndView) {
        modelAndView.setViewName(USER_SESSION);
        return modelAndView;
    }

}
