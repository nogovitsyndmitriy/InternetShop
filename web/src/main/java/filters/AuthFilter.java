package filters;


import command.enums.ControllerType;
import handler.RequestHandler;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        ControllerType type = RequestHandler.getCommand(req);
        if (ControllerType.CREATE_ORDER.equals(type)) {
            String contextPath = req.getContextPath();
            HttpSession session = req.getSession();
            if (session.getAttribute("user") == null) {
                resp.sendRedirect(contextPath + "/frontController?command=login");
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
