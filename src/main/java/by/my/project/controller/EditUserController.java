package by.my.project.controller;

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
@RequestMapping(path = USER_SESSION + "/" + USER_PROFILE)
public class EditUserController {
    private final JpaUserService userService;

    @GetMapping(path = EDIT_USER)
    public ModelAndView userEdit(ModelAndView modelAndView, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(USER_SESSION);
        modelAndView.addObject(USER_SESSION, user);
        return ModelAndViewUtil.getModelAndView(modelAndView, USER, EDIT_USER);
    }

    @GetMapping(path = EDIT_USER_EMAIL)
    public ModelAndView userEditEmailSetForm(ModelAndView modelAndView) {
        return ModelAndViewUtil.getModelAndView(modelAndView, EMAIL, EDIT_USER);
    }

    @PostMapping(path = EDIT_USER_EMAIL)
    public ModelAndView userEditEmailGetForm(@RequestParam(EMAIL) String email, HttpServletRequest request,
                                                     ModelAndView modelAndView) {
        if (email.equals("")) {
            modelAndView.addObject(MESSAGE_ERROR, MESSAGE_ERROR_FOR_EMAIL);
            return ModelAndViewUtil.getModelAndView(modelAndView, EMAIL, EDIT_USER);
        }
        User user = (User) request.getSession().getAttribute(USER_SESSION);
        if (userService.findUserByEmail(email) != null) {
            modelAndView.addObject(MESSAGE_ERROR, MESSAGE_ERROR_FOR_USER_BY_EMAIL);
            return ModelAndViewUtil.getModelAndView(modelAndView, EMAIL, EDIT_USER);
        }
        user.setEmail(email);
        userService.updateUser(user);
        modelAndView.setViewName(REDIRECT + USER_SESSION + "/" + USER_PROFILE);
        return modelAndView;
    }

    @GetMapping(path = EDIT_USER_PASSWORD)
    public ModelAndView userEditPasswordSetForm(ModelAndView modelAndView) {
        return ModelAndViewUtil.getModelAndView(modelAndView, PASSWORD, EDIT_USER);
    }

    @PostMapping(path = EDIT_USER_PASSWORD)
    public ModelAndView userEditPasswordGetForm(@RequestParam(PASSWORD) String password,
                                                        @RequestParam(NEW_PASSWORD) String newPassword,
                                                        @RequestParam(REPEAT_NEW_PASSWORD) String repeatNewPassword,
                                                        HttpServletRequest request, ModelAndView modelAndView) {
        if (password.equals("") || newPassword.equals("") || repeatNewPassword.equals("")) {
            modelAndView.addObject(MESSAGE_ERROR_FOR_NULL, MESSAGE_ERROR_FOR_NULL_VIEW);
            return ModelAndViewUtil.getModelAndView(modelAndView, PASSWORD, EDIT_USER);
        }
        User user = (User) request.getSession().getAttribute(USER_SESSION);
        if (!user.getPassword().equals(PasswordUtil.convertPassToMD5(password))) {
            modelAndView.addObject(MESSAGE_ERROR, MESSAGE_ERROR_FOR_PASSWORD);
            return ModelAndViewUtil.getModelAndView(modelAndView, PASSWORD, EDIT_USER);
        }
        if (!newPassword.equals(repeatNewPassword)) {
            modelAndView.addObject(MESSAGE_ERROR_REPEAT, MESSAGE_ERROR_FOR_REPEAT_PASSWORD);
            return ModelAndViewUtil.getModelAndView(modelAndView, PASSWORD, EDIT_USER);
        }
        user.setPassword(PasswordUtil.convertPassToMD5(newPassword));
        userService.updateUser(user);
        modelAndView.setViewName(REDIRECT + USER_SESSION + "/" + USER_PROFILE);
        return modelAndView;
    }
}
