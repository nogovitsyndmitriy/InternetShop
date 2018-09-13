package command.impl;

import command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BasketController implements Controller {
    ItemService itemService = ItemServiceImpl.getINSTANCE();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user  == null ||"".equals(user)) {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/frontController?command=login");
        } else {
            request.getSession().setAttribute("item", itemService.getAllByUserId(user.getId()));
            request.setAttribute("item", itemService.getAllByUserId(user.getId()));
            RequestDispatcher dispatcher = request.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(request, response);
        }
    }
}
