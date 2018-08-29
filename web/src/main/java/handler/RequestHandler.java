package handler;

import command.enums.ControllerType;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static command.enums.ControllerType.HOME;

public class RequestHandler {
    public static ControllerType getCommand(HttpServletRequest request) {
        String parameter = request.getParameter("command");

        ControllerType type = ControllerType.getByPageName(parameter);
        request.setAttribute("title", type.getPageName());
        HttpSession session = request.getSession();
        String pageName = (String) session.getAttribute("pageName");
        if (pageName != null) {
            session.setAttribute("prevPage", pageName);
            session.setAttribute("pageName", type.getPageName());
            session.setAttribute("pagePath", type.getPagePath());
        } else {
            session.setAttribute("prevPage", type.getPageName());
            session.setAttribute("pageName", HOME.getPageName());
            session.setAttribute("pagePath", HOME.getPagePath());
        }
        return type;
    }
}
