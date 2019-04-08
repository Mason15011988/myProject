package by.my.project.controller;

import by.my.project.entity.AddressHotel;
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

import static by.my.project.constant.Constants.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = ADMIN_HOTEL_SESSION + "/" + ADMIN_HOTEL_PROFILE)
public class EditHotelController {

    private final JpaAdminHotelService adminHotelService;

    @GetMapping(path = DELETE_HOTEL)
    public ModelAndView deleteHotel(ModelAndView modelAndView, HttpServletRequest request) {
        Hotel hotelForDel = (Hotel) request.getSession().getAttribute(HOTEL);
        adminHotelService.deleteHotel(hotelForDel);
        modelAndView.setViewName(REDIRECT + ADMIN_HOTEL_SESSION + "/" + ADMIN_HOTEL_PROFILE);
        return modelAndView;
    }

    @GetMapping(path = DELETE_ROOM)
    public ModelAndView deleteRoom(@PathVariable(ID) Integer id, ModelAndView modelAndView, HttpServletRequest request) {
        Hotel hotel = (Hotel) request.getSession().getAttribute(HOTEL);
        Room roomForDel = hotel.getRoomList().get(id);
        adminHotelService.deleteRoom(roomForDel);
        modelAndView.setViewName(REDIRECT + ADMIN_HOTEL_SESSION + "/" + ADMIN_HOTEL_PROFILE);
        return modelAndView;
    }

    @GetMapping(path = EDIT_HOTEL)
    public ModelAndView editHotelSetForm(ModelAndView modelAndView) {
        modelAndView.addObject(NEW_HOTEL, new Hotel());
        return ModelAndViewUtil.getModelAndView(modelAndView, HOTEL, EDIT_HOTEL);
    }

    @PostMapping(path = EDIT_HOTEL)
    public ModelAndView editHotelGetForm(@Valid @ModelAttribute(NEW_HOTEL) Hotel hotel, BindingResult bindingResult,
                                         ModelAndView modelAndView, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return ModelAndViewUtil.getModelAndView(modelAndView, HOTEL, EDIT_HOTEL);
        }
        Hotel hotelForUpdate = (Hotel) request.getSession().getAttribute(HOTEL);
        setHotelForUpdate(hotel, hotelForUpdate);
        adminHotelService.updateHotel(hotelForUpdate);
        modelAndView.setViewName(REDIRECT + ADMIN_HOTEL_SESSION + "/" + ADMIN_HOTEL_PROFILE);
        return modelAndView;
    }

    private void setHotelForUpdate(@ModelAttribute(NEW_HOTEL) @Valid Hotel hotel, Hotel hotelForUpdate) {
        hotelForUpdate.setName(hotel.getName());
        hotelForUpdate.setStars(hotel.getStars());
        hotelForUpdate.setDescription(hotel.getDescription());
    }

    @GetMapping(path = EDIT_HOTEL_ADDRESS)
    public ModelAndView editHotelAddressSetForm(ModelAndView modelAndView) {
        modelAndView.addObject(NEW_ADDRESS, new AddressHotel());
        return ModelAndViewUtil.getModelAndView(modelAndView, HOTEL_ADDRESS, EDIT_HOTEL);

    }

    @PostMapping(path = EDIT_HOTEL_ADDRESS)
    public ModelAndView editHotelAddressGetForm(@Valid @ModelAttribute(NEW_ADDRESS) AddressHotel addressHotel,
                                                BindingResult bindingResult, ModelAndView modelAndView,
                                                HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return ModelAndViewUtil.getModelAndView(modelAndView, HOTEL_ADDRESS, EDIT_HOTEL);
        }
        Hotel hotelForUpdate = (Hotel) request.getSession().getAttribute(HOTEL);
        hotelForUpdate.setAddressHotel(addressHotel);
        adminHotelService.updateHotel(hotelForUpdate);
        modelAndView.setViewName(REDIRECT + ADMIN_HOTEL_SESSION + "/" + ADMIN_HOTEL_PROFILE);
        return modelAndView;
    }

    @GetMapping(path = EDIT_ROOM)
    public ModelAndView editHotelRoomSetForm(@PathVariable(ID) Integer id, ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.addObject(NEW_ROOM, new Room());
        request.getSession().setAttribute(ID, id);
        return ModelAndViewUtil.getModelAndView(modelAndView, HOTEL_ROOM, EDIT_HOTEL);
    }

    @PostMapping(path = EDIT_HOTEL_ROOM)
    public ModelAndView editHotelRoomGetForm(@Valid @ModelAttribute(NEW_ROOM) Room room,
                                             BindingResult bindingResult, ModelAndView modelAndView,
                                             HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return ModelAndViewUtil.getModelAndView(modelAndView, HOTEL_ROOM, EDIT_HOTEL);
        }

        Hotel hotel = (Hotel) request.getSession().getAttribute(HOTEL);
        room.setHotel(hotel);
        Integer id = (Integer) request.getSession().getAttribute(ID);
        Room roomForUpdate = hotel.getRoomList().get(id);
        if (!room.getNumberRoom().equals(roomForUpdate.getNumberRoom())) {
            if (adminHotelService.findNumberRoom(room) != null) {
                modelAndView.addObject(MESSAGE_ERROR, MESSAGE_ERROR_FOR_ROOM);
                return ModelAndViewUtil.getModelAndView(modelAndView, HOTEL_ROOM, EDIT_HOTEL);
            }
        }
        roomForUpdate.setNumberOfSeats(room.getNumberOfSeats());
        roomForUpdate.setPrice(room.getPrice());
        roomForUpdate.setNumberRoom(room.getNumberRoom());
        adminHotelService.updateRoom(roomForUpdate);
        modelAndView.setViewName(REDIRECT + ADMIN_HOTEL_SESSION + "/" + ADMIN_HOTEL_PROFILE);
        return modelAndView;
    }
}
