package by.my.project.controller;

import by.my.project.entity.AdminHotel;
import by.my.project.entity.Hotel;
import by.my.project.service.JpaAdminHotelService;
import by.my.project.util.ModelAndViewUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
        return ModelAndViewUtil.getModelAndView(modelAndView, ADMIN_HOTEL, PROFILE);
    }

    @GetMapping(path = HOTELS)
    public ModelAndView showHotels(ModelAndView modelAndView, HttpServletRequest request) {
        AdminHotel adminHotelSession = (AdminHotel) request.getSession().getAttribute(ADMIN_HOTEL_SESSION);
        AdminHotel adminHotel = adminHotelService.findAdminHotel(adminHotelSession);
        List<Hotel> hotels = adminHotel.getHotelList();
        modelAndView.setViewName(HOTELS);
        if (hotels.size() == 0) {
            modelAndView.addObject(MESSAGE_ERROR, NO_HOTELS);
            modelAndView.addObject(HOTELS, hotels);
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
