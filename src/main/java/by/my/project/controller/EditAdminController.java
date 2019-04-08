package by.my.project.controller;


import by.my.project.entity.AdminHotel;
import by.my.project.service.JpaAdminHotelService;
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
@RequestMapping(path = ADMIN_HOTEL_SESSION + "/" + ADMIN_HOTEL_PROFILE)
public class EditAdminController {

    private final JpaAdminHotelService adminHotelService;

    @GetMapping(path = EDIT_ADMIN_HOTEL)
    public ModelAndView adminHotelEdit(ModelAndView modelAndView, HttpServletRequest request) {
        AdminHotel adminHotel = (AdminHotel) request.getSession().getAttribute(ADMIN_HOTEL_SESSION);
        modelAndView.addObject(ADMIN_HOTEL_SESSION, adminHotel);
        return ModelAndViewUtil.getModelAndView(modelAndView, ADMIN_HOTEL, EDIT_ADMIN_HOTEL);
    }

    @GetMapping(path = EDIT_ADMIN_HOTEL_EMAIL)
    public ModelAndView adminHotelEditEmailSetForm(ModelAndView modelAndView) {
        return ModelAndViewUtil.getModelAndView(modelAndView, EMAIL, EDIT_ADMIN_HOTEL);
    }

    @PostMapping(path = EDIT_ADMIN_HOTEL_EMAIL)
    public ModelAndView adminHotelEditEmailGetForm(@RequestParam(EMAIL) String email, HttpServletRequest request,
                                                   ModelAndView modelAndView) {

        if (email.equals("")) {
            modelAndView.addObject(MESSAGE_ERROR, MESSAGE_ERROR_FOR_EMAIL);
            return ModelAndViewUtil.getModelAndView(modelAndView, EMAIL, EDIT_ADMIN_HOTEL);
        }

        AdminHotel adminHotel = (AdminHotel) request.getSession().getAttribute(ADMIN_HOTEL_SESSION);
        if (adminHotelService.findAdminHotelByEmail(email) != null) {
            modelAndView.addObject(MESSAGE_ERROR, MESSAGE_ERROR_FOR_ADMIN_BY_EMAIL);
            return ModelAndViewUtil.getModelAndView(modelAndView, EMAIL, EDIT_ADMIN_HOTEL);
        }
        adminHotel.setEmail(email);
        adminHotelService.updateAdminHotel(adminHotel);
        modelAndView.setViewName(REDIRECT + ADMIN_HOTEL_SESSION + "/" + ADMIN_HOTEL_PROFILE);
        return modelAndView;
    }

    @GetMapping(path = EDIT_ADMIN_HOTEL_PASSWORD)
    public ModelAndView adminHotelEditPasswordSetForm(ModelAndView modelAndView) {
        return ModelAndViewUtil.getModelAndView(modelAndView, PASSWORD, EDIT_ADMIN_HOTEL);
    }

    @PostMapping(path = EDIT_ADMIN_HOTEL_PASSWORD)
    public ModelAndView adminHotelEditPasswordGetForm(@RequestParam(PASSWORD) String password,
                                                      @RequestParam(NEW_PASSWORD) String newPassword,
                                                      @RequestParam(REPEAT_NEW_PASSWORD) String repeatNewPassword,
                                                      HttpServletRequest request, ModelAndView modelAndView) {
        if (password.equals("") || newPassword.equals("") || repeatNewPassword.equals("")) {
            modelAndView.addObject(MESSAGE_ERROR_FOR_NULL, MESSAGE_ERROR_FOR_NULL_VIEW);
            return ModelAndViewUtil.getModelAndView(modelAndView, PASSWORD, EDIT_ADMIN_HOTEL);
        }
        AdminHotel adminHotel = (AdminHotel) request.getSession().getAttribute(ADMIN_HOTEL_SESSION);
        if (!adminHotel.getPassword().equals(PasswordUtil.convertPassToMD5(password))) {
            modelAndView.addObject(MESSAGE_ERROR, MESSAGE_ERROR_FOR_PASSWORD);
            return ModelAndViewUtil.getModelAndView(modelAndView, PASSWORD, EDIT_ADMIN_HOTEL);
        }
        if (!newPassword.equals(repeatNewPassword)) {
            modelAndView.addObject(MESSAGE_ERROR_REPEAT, MESSAGE_ERROR_FOR_REPEAT_PASSWORD);
            return ModelAndViewUtil.getModelAndView(modelAndView, PASSWORD, EDIT_ADMIN_HOTEL);
        }
        adminHotel.setPassword(PasswordUtil.convertPassToMD5(newPassword));
        adminHotelService.updateAdminHotel(adminHotel);
        modelAndView.setViewName(REDIRECT + ADMIN_HOTEL_SESSION + "/" + ADMIN_HOTEL_PROFILE);
        return modelAndView;
    }
}
