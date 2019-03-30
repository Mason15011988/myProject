package by.my.project.controller;

import by.my.project.constant.Role;
import by.my.project.entity.DateOfBooking;
import by.my.project.entity.Search;
import by.my.project.service.JpaUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.SimpleDateFormat;

import static by.my.project.constant.Constants.*;
@RequiredArgsConstructor
@Controller
@RequestMapping(path = "/")
public class IndexController {
    private final JpaUserService userService;

    @GetMapping
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.addObject("newSearch", new Search());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView indexSearch(@RequestParam("country") String country, @RequestParam("numberSeat") String numberSeat,
                                    @RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate,
                                    ModelAndView modelAndView) {
        Date nowDate = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        if (sdf.format(startDate).compareTo(sdf.format(nowDate)) < 0) {
            modelAndView.addObject("errorNowDate", "дата должна быть больше или равна текущей ");
            modelAndView.setViewName("index");
            return modelAndView;
        }
        if (endDate.compareTo(startDate) < 0) {
            modelAndView.addObject("errorDate", "дата отьезда должна быть больше даты заезда");
            modelAndView.setViewName("index");
            return modelAndView;
        }

        Search search = new Search();
        search.setCountry(country);

//         java.util.Date date = new java.util.Date(startDate.getTime());
//         java.util.Date date2 = new java.util.Date(endDate.getTime());

        search.setNumberSeat(Integer.valueOf(numberSeat));
        search.setStartDate(startDate);
        search.setEndDate(endDate);
        DateOfBooking dateOfBooking = new DateOfBooking();

        dateOfBooking.setStartDate(startDate);
        dateOfBooking.setEndDate(endDate);
        userService.addDate(dateOfBooking);
        modelAndView.addObject("search", search);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping(path = EXIT)
    public ModelAndView exit(ModelAndView modelAndView, HttpServletRequest request) {
        request.getSession().setAttribute(ROLE, Role.GUEST);
        modelAndView.setViewName(REDIRECT);
        return modelAndView;
    }
}
