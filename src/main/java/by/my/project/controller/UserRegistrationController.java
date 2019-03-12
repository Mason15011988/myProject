package by.my.project.controller;

import static by.my.project.constant.Constants.REGISTRATION;

import by.my.project.entity.User;
import by.my.project.service.JpaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping(path = "/registration")
public class UserRegistrationController {
    @Autowired
    private JpaUserService userService;

    @GetMapping
    public ModelAndView setForm(ModelAndView modelAndView) {
        modelAndView.setViewName(REGISTRATION);
        modelAndView.addObject("newUser", new User());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView getForm (@Valid @ModelAttribute ("newUser") User user, BindingResult bindingResult, HttpServletRequest httpServletRequest,
                                 ModelAndView modelAndView){

        httpServletRequest.getSession().setAttribute("user", user);
        if (bindingResult.hasErrors()){
            modelAndView.setViewName(REGISTRATION);
            return modelAndView;
        }
        userService.addUser(user);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
