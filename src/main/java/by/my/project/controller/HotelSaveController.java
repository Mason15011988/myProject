package by.my.project.controller;

import by.my.project.entity.AddressHotel;
import by.my.project.entity.AdminHotel;
import by.my.project.entity.Hotel;
import by.my.project.entity.Room;
import by.my.project.service.JpaAdminHotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static by.my.project.constant.Constants.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = ADMIN_HOTEL_SESSION + "/" + ADMIN_HOTEL_PROFILE + "/" + HOTEL)
public class HotelSaveController {
    private final JpaAdminHotelService adminHotelService;

    @GetMapping(path = ADD_HOTEL_ADDRESS)
    public ModelAndView setFormAddress(ModelAndView modelAndView) {
        modelAndView.setViewName(ADD_HOTEL_ADDRESS);
        modelAndView.addObject(NEW_ADDRESS, new AddressHotel());
        return modelAndView;
    }

    @PostMapping(path = ADD_HOTEL_ADDRESS)
    public ModelAndView getFormAddress(@Valid @ModelAttribute(NEW_ADDRESS) AddressHotel addressHotel,
                                       BindingResult bindingResult,
                                       ModelAndView modelAndView, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(ADD_HOTEL_ADDRESS);
            return modelAndView;
        }
        request.getSession().setAttribute(HOTEL_ADDRESS, addressHotel);
        modelAndView.setViewName(REDIRECT + ADMIN_HOTEL_SESSION + "/" + ADMIN_HOTEL_PROFILE + "/" + HOTEL + "/" + ADD_HOTEL);
        return modelAndView;
    }

    @GetMapping(path = ADD_HOTEL)
    public ModelAndView setFormHotel(ModelAndView modelAndView) {
        modelAndView.setViewName(ADD_HOTEL);
        modelAndView.addObject(NEW_HOTEL, new Hotel());
        return modelAndView;
    }

    @PostMapping(path = ADD_HOTEL)
    public ModelAndView getFormHotel(@Valid @ModelAttribute(NEW_HOTEL) Hotel hotel, BindingResult bindingResult,
                                     ModelAndView modelAndView, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(ADD_HOTEL);
            return modelAndView;
        }
        AdminHotel adminHotel = (AdminHotel) request.getSession().getAttribute(ADMIN_HOTEL_SESSION);
        AddressHotel addressHotel = (AddressHotel) request.getSession().getAttribute(HOTEL_ADDRESS);
        hotel.setAdminHotel(adminHotel);
        hotel.setAddressHotel(addressHotel);
        adminHotelService.addHotel(hotel);
        request.getSession().setAttribute(NEW_HOTEL, hotel);
        modelAndView.setViewName(REDIRECT + ADMIN_HOTEL_SESSION + "/" + ADMIN_HOTEL_PROFILE + "/" + HOTEL + "/" + ADD_ROOM);
        return modelAndView;
    }

    @GetMapping(path = ADD_ROOM)
    public ModelAndView setFormRoom(ModelAndView modelAndView) {
        modelAndView.setViewName(ADD_ROOM);
        modelAndView.addObject(NEW_ROOM, new Room());
        return modelAndView;
    }

    @PostMapping(path = ADD_ROOM)
    public ModelAndView getFormRoom(@Valid @ModelAttribute(NEW_ROOM) Room room, BindingResult bindingResult,
                                    ModelAndView modelAndView, HttpServletRequest request) throws CloneNotSupportedException {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(ADD_ROOM);
            return modelAndView;
        }
        Hotel hotel = (Hotel) request.getSession().getAttribute(NEW_HOTEL);
        room.setHotel(hotel);
        int count = room.getCount();
        List<Room> rooms = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Room room1 = (Room) room.clone();
            rooms.add(room1);
        }
        hotel.getRoomList().addAll(rooms);
        modelAndView.setViewName(REDIRECT + ADMIN_HOTEL_SESSION + "/" + ADMIN_HOTEL_PROFILE + "/" + HOTEL + "/" + CHOICE_ADD_ROOM);
        return modelAndView;
    }

    @GetMapping(path = CHOICE_ADD_ROOM)
    public ModelAndView choice(ModelAndView modelAndView) {
        modelAndView.setViewName(CHOICE_ADD_ROOM);
        return modelAndView;
    }

    @GetMapping(path = SAVE_ROOM)
    public ModelAndView saveHotel(ModelAndView modelAndView, HttpServletRequest request) {
        Hotel hotel = (Hotel) request.getSession().getAttribute(NEW_HOTEL);
        adminHotelService.updateHotel(hotel);
        modelAndView.setViewName(REDIRECT + ADMIN_HOTEL_SESSION + "/" + ADMIN_HOTEL_PROFILE);
        return modelAndView;
    }
}
