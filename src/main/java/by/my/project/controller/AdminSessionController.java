package by.my.project.controller;

import by.my.project.constant.Role;
import by.my.project.entity.AddressHotel;
import by.my.project.entity.AdminHotel;
import by.my.project.entity.Hotel;
import by.my.project.entity.Room;
import by.my.project.service.JpaAdminHotelService;
import by.my.project.util.ModelAndViewUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

import static by.my.project.constant.Constants.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = ADMIN_HOTEL_SESSION)
public class AdminSessionController {
    private final JpaAdminHotelService adminHotelService;

    @GetMapping
    public ModelAndView adminSession(ModelAndView modelAndView) {
        return ModelAndViewUtil.getModelAndView(modelAndView, ADMIN_HOTEL, SESSION);
    }


    @GetMapping(path = ADMIN_HOTEL_PROFILE)
    public ModelAndView adminProfile(ModelAndView modelAndView, HttpServletRequest request) {
        AdminHotel adminHotel = (AdminHotel) request.getSession().getAttribute(ADMIN_HOTEL_SESSION);
        modelAndView.addObject(ADMIN_HOTEL_SESSION, adminHotel);
        return ModelAndViewUtil.getModelAndView(modelAndView, ADMIN_HOTEL,PROFILE );
    }

    @GetMapping(path = HOTELS)
    public ModelAndView showHotels(ModelAndView modelAndView, HttpServletRequest request) {
        AdminHotel adminHotelSession = (AdminHotel) request.getSession().getAttribute(ADMIN_HOTEL_SESSION);
        AdminHotel adminHotel = adminHotelService.findAdminHotel(adminHotelSession);
        List<Hotel> hotels = adminHotel.getHotelList();
        if (hotels.size() == 0) {
            modelAndView.addObject(MESSAGE_ERROR, NO_HOTEL);
            modelAndView.addObject(HOTELS, hotels);
            modelAndView.setViewName(HOTELS);
            return modelAndView;
        }

        request.getSession().setAttribute(HOTELS, hotels);
        modelAndView.addObject(HOTELS, hotels);
        return modelAndView;
    }

    @GetMapping(path = HOTEL_SHOW)
    public ModelAndView showHotel(@PathVariable(ID) Integer id, HttpServletRequest request,
                                  ModelAndView modelAndView) {
        List<Hotel> hotels = (List<Hotel>) request.getSession().getAttribute(HOTELS);
        Hotel hotel = hotels.get(id - 1);
        request.getSession().setAttribute(HOTEL, hotel);
        modelAndView.addObject(HOTEL, hotel);
        modelAndView.setViewName(HOTEL);
        return modelAndView;
    }
}
