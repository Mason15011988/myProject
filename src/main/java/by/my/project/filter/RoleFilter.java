package by.my.project.filter;

import by.my.project.constant.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static by.my.project.constant.Constants.ROLE;

public class RoleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        if (httpServletRequest.getSession().isNew()) {
            httpServletRequest.getSession().setAttribute(ROLE, Role.GUEST);
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
