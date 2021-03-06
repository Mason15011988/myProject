package by.my.project.interceptor;

import by.my.project.constant.Role;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.my.project.constant.Constants.*;

@Component
public class IndexInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        Role attribute = (Role) request.getSession().getAttribute(ROLE);
        if (attribute.equals(Role.USER)) {
            modelAndView.setViewName(REDIRECT + USER_SESSION);
        }
        if (attribute.equals(Role.ADMIN_HOTEL)){
            modelAndView.setViewName(REDIRECT + ADMIN_HOTEL_SESSION);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

    }
}
