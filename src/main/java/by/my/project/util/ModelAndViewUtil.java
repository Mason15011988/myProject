package by.my.project.util;

import org.springframework.web.servlet.ModelAndView;

import static by.my.project.constant.Constants.ROLE;

public class ModelAndViewUtil {
    public static ModelAndView getModelAndView(ModelAndView modelAndView, String role, String setViewName) {
        modelAndView.addObject(ROLE, role);
        modelAndView.addObject(role, role);
        modelAndView.setViewName(setViewName);
        return modelAndView;
    }
}
