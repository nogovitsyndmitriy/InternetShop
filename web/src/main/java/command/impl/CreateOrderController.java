package command.impl;

import command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateOrderController implements Controller {
    OrderService orderService = OrderServiceImpl.getINSTANCE();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            long productId = Long.parseLong(request.getParameter("productId"));
            Order orders = orderService.createOrder(user.getId(), productId, 0);

            request.setAttribute("orders", orders);
            RequestDispatcher dispatcher = request.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(request, response);
        } else {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/frontController?command=login");
            return;
        }
    }
}
