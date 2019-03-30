package by.my.project.controller;

import by.my.project.entity.AdminHotel;
import by.my.project.entity.User;
import by.my.project.service.JpaUserService;
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
        modelAndView.setViewName(USER_SESSION);
        return modelAndView;
    }

    @GetMapping(path = USER_PROFILE)
    public ModelAndView adminProfile(ModelAndView modelAndView, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(USER_SESSION);
        modelAndView.addObject(USER_SESSION, user);
        modelAndView.setViewName(USER_PROFILE);
        return modelAndView;
    }

    @GetMapping(path = USER_PROFILE + "/" + EDIT_USER)
    public ModelAndView adminProfileEdit(ModelAndView modelAndView, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(USER_SESSION);
        modelAndView.addObject(USER_SESSION, user);
        modelAndView.setViewName(EDIT_USER);
        return modelAndView;
    }

    @GetMapping(path = USER_PROFILE + "/" + EDIT_USER_EMAIL)
    public ModelAndView adminProfileEditEmailSetForm(ModelAndView modelAndView) {
        modelAndView.setViewName(EDIT_USER_EMAIL);
        return modelAndView;
    }

    @PostMapping(path = USER_PROFILE + "/" + EDIT_USER_EMAIL)
    public ModelAndView adminProfileEditEmailGetForm(@RequestParam(EMAIL) String email, HttpServletRequest request,
                                                     ModelAndView modelAndView) {
        User user = (User) request.getSession().getAttribute(USER_SESSION);
        if (userService.findUserByEmail(email) != null) {
            modelAndView.addObject(MESSAGE_ERROR, MESSAGE_ERROR_FOR_USER_BY_EMAIL);
            modelAndView.setViewName(EDIT_USER_EMAIL);
            return modelAndView;
        }
        user.setEmail(email);
        userService.updateUser(user);
        modelAndView.setViewName(USER_PROFILE);
        return modelAndView;
    }

    @GetMapping(path = USER_PROFILE + "/" + EDIT_USER_PASSWORD)
    public ModelAndView adminProfileEditPasswordSetForm(ModelAndView modelAndView){
        modelAndView.setViewName(EDIT_USER_PASSWORD);
        return modelAndView;
    }

    @PostMapping(path = USER_PROFILE + "/" + EDIT_USER_PASSWORD)
    public ModelAndView adminProfileEditPasswordGetForm(@RequestParam(PASSWORD) String password,
                                                        @RequestParam(NEW_PASSWORD) String newPassword,
                                                        @RequestParam(REPEAT_NEW_PASSWORD) String repeatNewPassword,
                                                        HttpServletRequest request,ModelAndView modelAndView){
        User user = (User) request.getSession().getAttribute(USER_SESSION);
        if (!user.getPassword().equals(PasswordUtil.convertPassToMD5(password))){
            modelAndView.addObject(MESSAGE_ERROR, MESSAGE_ERROR_FOR_PASSWORD);
            modelAndView.setViewName(EDIT_USER_PASSWORD);
            return modelAndView;
        }
        if (!newPassword.equals(repeatNewPassword)){
            modelAndView.addObject(MESSAGE_ERROR_REPEAT, MESSAGE_ERROR_FOR_REPEAT_PASSWORD);
            modelAndView.setViewName(EDIT_USER_PASSWORD);
            return modelAndView;
        }
        user.setPassword(PasswordUtil.convertPassToMD5(newPassword));
        userService.updateUser(user);
        modelAndView.setViewName(USER_PROFILE);
        return modelAndView;
    }
}
