package by.my.project.controller;

import by.my.project.service.JpaAdminHotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static by.my.project.constant.Constants.ADMIN_SESSION;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/" + ADMIN_SESSION)
public class AdminSessionController {
    private final JpaAdminHotelService adminHotelService;

    @GetMapping
    public ModelAndView userSession(ModelAndView modelAndView) {
        modelAndView.setViewName(ADMIN_SESSION);
        return modelAndView;
    }
}
