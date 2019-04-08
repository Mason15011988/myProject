package by.my.project.controller;

import by.my.project.entity.AddressHotel;
import by.my.project.entity.AdminHotel;
import by.my.project.entity.Hotel;
import by.my.project.entity.Room;
import by.my.project.service.JpaAdminHotelService;
import by.my.project.util.ModelAndViewUtil;
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
    public ModelAndView hotelAddressSetForm(ModelAndView modelAndView) {
        modelAndView.addObject(NEW_ADDRESS, new AddressHotel());
        return ModelAndViewUtil.getModelAndView(modelAndView, HOTEL_ADDRESS, ADD_HOTEL);
    }

    @PostMapping(path = ADD_HOTEL_ADDRESS)
    public ModelAndView hotelAddressGetForm(@Valid @ModelAttribute(NEW_ADDRESS) AddressHotel addressHotel,
                                            BindingResult bindingResult,
                                            ModelAndView modelAndView, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return ModelAndViewUtil.getModelAndView(modelAndView, HOTEL_ADDRESS, ADD_HOTEL);
        }
        request.getSession().setAttribute(HOTEL_ADDRESS, addressHotel);
        modelAndView.setViewName(REDIRECT + ADMIN_HOTEL_SESSION + "/" + ADMIN_HOTEL_PROFILE + "/" + HOTEL + "/" + ADD_HOTEL);
        return modelAndView;
    }

    @GetMapping(path = ADD_HOTEL)
    public ModelAndView hotelSetForm(ModelAndView modelAndView) {
        modelAndView.addObject(NEW_HOTEL, new Hotel());
        return ModelAndViewUtil.getModelAndView(modelAndView, HOTEL, ADD_HOTEL);
    }

    @PostMapping(path = ADD_HOTEL)
    public ModelAndView hotelGetForm(@Valid @ModelAttribute(NEW_HOTEL) Hotel hotel, BindingResult bindingResult,
                                     ModelAndView modelAndView, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return ModelAndViewUtil.getModelAndView(modelAndView, HOTEL, ADD_HOTEL);

        }
        AdminHotel adminHotel = (AdminHotel) request.getSession().getAttribute(ADMIN_HOTEL_SESSION);
        AddressHotel addressHotel = (AddressHotel) request.getSession().getAttribute(HOTEL_ADDRESS);
        hotel.setAdminHotel(adminHotel);
        hotel.setAddressHotel(addressHotel);
        adminHotelService.addHotel(hotel);
        request.getSession().setAttribute(HOTEL, hotel);
        modelAndView.setViewName(REDIRECT + ADMIN_HOTEL_SESSION + "/" + ADMIN_HOTEL_PROFILE + "/" + HOTEL + "/" + ADD_HOTEL_ROOM);
        return modelAndView;
    }

    @GetMapping(path = ADD_HOTEL_ROOM)
    public ModelAndView hotelRoomSetForm(ModelAndView modelAndView) {
        modelAndView.addObject(NEW_ROOM, new Room());
        return ModelAndViewUtil.getModelAndView(modelAndView, HOTEL_ROOM, ADD_HOTEL);
    }

    @PostMapping(path = ADD_HOTEL_ROOM)
    public ModelAndView hotelRoomGetForm(@Valid @ModelAttribute(NEW_ROOM) Room room, BindingResult bindingResult,
                                         ModelAndView modelAndView, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return ModelAndViewUtil.getModelAndView(modelAndView, HOTEL_ROOM, ADD_HOTEL);
        }
        Hotel hotel = (Hotel) request.getSession().getAttribute(HOTEL);
        room.setHotel(hotel);
        if (adminHotelService.findNumberRoom(room) != null) {
            modelAndView.addObject(MESSAGE_ERROR, MESSAGE_ERROR_FOR_ROOM);
            return ModelAndViewUtil.getModelAndView(modelAndView, HOTEL_ROOM, ADD_HOTEL);
        }
        List<Room> rooms = new ArrayList<>();
        rooms.add(room);
        hotel.setRoomList(rooms);
        adminHotelService.updateHotel(hotel);
        modelAndView.setViewName(REDIRECT + ADMIN_HOTEL_SESSION + "/" + ADMIN_HOTEL_PROFILE + "/" + HOTEL + "/" + CHOICE_ADD_HOTEL_ROOM);
        return modelAndView;
    }

    @GetMapping(path = CHOICE_ADD_HOTEL_ROOM)
    public ModelAndView choice(ModelAndView modelAndView) {
        return ModelAndViewUtil.getModelAndView(modelAndView, CHOICE, ADD_HOTEL);
    }
}
