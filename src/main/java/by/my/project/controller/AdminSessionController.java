package by.my.project.controller;

import by.my.project.entity.AddressHotel;
import by.my.project.entity.AdminHotel;
import by.my.project.entity.Hotel;
import by.my.project.service.JpaAdminHotelService;
import by.my.project.util.PasswordUtil;
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
        modelAndView.setViewName(ADMIN_HOTEL_SESSION);
        return modelAndView;
    }


    @GetMapping(path = ADMIN_HOTEL_PROFILE)
    public ModelAndView adminProfile(ModelAndView modelAndView, HttpServletRequest request) {
        AdminHotel adminHotel = (AdminHotel) request.getSession().getAttribute(ADMIN_HOTEL_SESSION);
        modelAndView.addObject(ADMIN_HOTEL_SESSION, adminHotel);
        modelAndView.setViewName(ADMIN_HOTEL_PROFILE);
        return modelAndView;
    }

    @GetMapping(path = ADMIN_HOTEL_PROFILE + "/" + EDIT_ADMIN_HOTEL)
    public ModelAndView adminProfileEdit(ModelAndView modelAndView, HttpServletRequest request) {
        AdminHotel adminHotel = (AdminHotel) request.getSession().getAttribute(ADMIN_HOTEL_SESSION);
        modelAndView.addObject(ADMIN_HOTEL_SESSION, adminHotel);
        modelAndView.setViewName(EDIT_ADMIN_HOTEL);
        return modelAndView;
    }

    @GetMapping(path = ADMIN_HOTEL_PROFILE + "/" + EDIT_ADMIN_HOTEL_EMAIL)
    public ModelAndView adminProfileEditEmailSetForm(ModelAndView modelAndView) {
        modelAndView.setViewName(EDIT_ADMIN_HOTEL_EMAIL);
        return modelAndView;
    }

    @PostMapping(path = ADMIN_HOTEL_PROFILE + "/" + EDIT_ADMIN_HOTEL_EMAIL)
    public ModelAndView adminProfileEditEmailGetForm(@RequestParam(EMAIL) String email, HttpServletRequest request,
                                                     ModelAndView modelAndView) {
        AdminHotel adminHotel = (AdminHotel) request.getSession().getAttribute(ADMIN_HOTEL_SESSION);
        if (adminHotelService.findAdminHotelByEmail(email) != null) {
            modelAndView.addObject(MESSAGE_ERROR, MESSAGE_ERROR_FOR_ADMIN_BY_EMAIL);
            modelAndView.setViewName(EDIT_ADMIN_HOTEL_EMAIL);
            return modelAndView;
        }
        adminHotel.setEmail(email);
        adminHotelService.updateAdminHotel(adminHotel);
        modelAndView.setViewName(ADMIN_HOTEL_PROFILE);
        return modelAndView;
    }

    @GetMapping(path = ADMIN_HOTEL_PROFILE + "/" + EDIT_ADMIN_HOTEL_PASSWORD)
    public ModelAndView adminProfileEditPasswordSetForm(ModelAndView modelAndView) {
        modelAndView.setViewName(EDIT_ADMIN_HOTEL_PASSWORD);
        return modelAndView;
    }

    @PostMapping(path = ADMIN_HOTEL_PROFILE + "/" + EDIT_ADMIN_HOTEL_PASSWORD)
    public ModelAndView adminProfileEditPasswordGetForm(@RequestParam(PASSWORD) String password,
                                                        @RequestParam(NEW_PASSWORD) String newPassword,
                                                        @RequestParam(REPEAT_NEW_PASSWORD) String repeatNewPassword,
                                                        HttpServletRequest request, ModelAndView modelAndView) {
        AdminHotel adminHotel = (AdminHotel) request.getSession().getAttribute(ADMIN_HOTEL_SESSION);
        if (!adminHotel.getPassword().equals(PasswordUtil.convertPassToMD5(password))) {
            modelAndView.addObject(MESSAGE_ERROR, MESSAGE_ERROR_FOR_PASSWORD);
            modelAndView.setViewName(EDIT_ADMIN_HOTEL_PASSWORD);
            return modelAndView;
        }
        if (!newPassword.equals(repeatNewPassword)) {
            modelAndView.addObject(MESSAGE_ERROR_REPEAT, MESSAGE_ERROR_FOR_REPEAT_PASSWORD);
            modelAndView.setViewName(EDIT_ADMIN_HOTEL_PASSWORD);
            return modelAndView;
        }
        adminHotel.setPassword(PasswordUtil.convertPassToMD5(newPassword));
        adminHotelService.updateAdminHotel(adminHotel);
        modelAndView.setViewName(ADMIN_HOTEL_PROFILE);
        return modelAndView;
    }

    @GetMapping(path = HOTELS)
    public ModelAndView showHotels(ModelAndView modelAndView, HttpServletRequest request) {
        AdminHotel adminHotelSession = (AdminHotel) request.getSession().getAttribute(ADMIN_HOTEL_SESSION);
        AdminHotel adminHotel1 = adminHotelService.findAdminHotel(adminHotelSession);
        modelAndView.setViewName(HOTELS);
        List<Hotel> hotels = adminHotel1.getHotelList();
        if (hotels.size() == 0) {
            modelAndView.addObject(MESSAGE_ERROR, NO_HOTEL);
            modelAndView.addObject(HOTELS, hotels);
            return modelAndView;
        }

        request.getSession().setAttribute(HOTELS, hotels);
        modelAndView.addObject(HOTELS, hotels);
        return modelAndView;
    }

    @GetMapping(path = HOTELS + "/show/{id}")
    public ModelAndView showHotel(@PathVariable(ID) Integer id, HttpServletRequest request,
                                  ModelAndView modelAndView) {
        List<Hotel> hotels = (List<Hotel>) request.getSession().getAttribute(HOTELS);
        Hotel hotel = hotels.get(id - 1);
        request.getSession().setAttribute(HOTEL, hotel);
        modelAndView.addObject("hotelShow", hotel);
        modelAndView.setViewName(HOTEL);
        return modelAndView;
    }

    @GetMapping(path = DELETE_HOTEL)
    public ModelAndView deleteHotel(ModelAndView modelAndView, HttpServletRequest request) {
        Hotel hotelForDel = (Hotel) request.getSession().getAttribute(HOTEL);
        adminHotelService.deleteHotel(hotelForDel);
        modelAndView.setViewName(REDIRECT + ADMIN_HOTEL_SESSION + "/" + ADMIN_HOTEL_PROFILE);
        return modelAndView;
    }

    @GetMapping(path = EDIT_HOTEL)
    public ModelAndView editHotelSetForm(ModelAndView modelAndView) {
        modelAndView.setViewName(EDIT_HOTEL);
        modelAndView.addObject(NEW_HOTEL, new Hotel());
        return modelAndView;
    }

    @PostMapping(path = EDIT_HOTEL)
    public ModelAndView editHotelGetForm(@Valid @ModelAttribute(NEW_HOTEL) Hotel hotel, BindingResult bindingResult,
                                            ModelAndView modelAndView, HttpServletRequest request){
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(EDIT_HOTEL);
            return modelAndView;
        }
        Hotel hotelForUpdate = (Hotel) request.getSession().getAttribute(HOTEL);
        hotelForUpdate.setName(hotel.getName());
        hotelForUpdate.setStars(hotel.getStars());
        hotelForUpdate.setDescription(hotel.getDescription());
        adminHotelService.updateHotel(hotelForUpdate);
        modelAndView.setViewName(REDIRECT + ADMIN_HOTEL_SESSION + "/" + ADMIN_HOTEL_PROFILE);
        return modelAndView;
    }


    @GetMapping(path = EDIT_HOTEL_ADDRESS)
    public ModelAndView editHotelAddressSetForm(ModelAndView modelAndView) {
        modelAndView.setViewName(EDIT_HOTEL_ADDRESS);
        modelAndView.addObject(NEW_ADDRESS, new AddressHotel());
        return modelAndView;
    }

    @PostMapping(path = EDIT_HOTEL_ADDRESS)
    public ModelAndView editHotelAddressGetForm(@Valid @ModelAttribute(NEW_ADDRESS) AddressHotel addressHotel,
                                                BindingResult bindingResult,ModelAndView modelAndView,
                                                HttpServletRequest request){
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(EDIT_HOTEL_ADDRESS);
            return modelAndView;
        }
        Hotel hotelForUpdate = (Hotel) request.getSession().getAttribute(HOTEL);
        hotelForUpdate.setAddressHotel(addressHotel);
        adminHotelService.updateHotel(hotelForUpdate);
        modelAndView.setViewName(REDIRECT + ADMIN_HOTEL_SESSION + "/" + ADMIN_HOTEL_PROFILE);
        return modelAndView;
    }
}
