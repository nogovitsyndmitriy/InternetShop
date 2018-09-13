package command.impl;

import auth.Encoder;
import command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController implements Controller {
    private UserService userService = UserServiceImpl.getINSTANCE();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login == null || "".equals(login) || password == null || "".equals(password)) {
            response.setHeader("errorMsg", "Invalid Login or Password");
            RequestDispatcher dispatcher = request.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(request, response);
            return;
        }
        User user = userService.getUserByAccount(login);
        if (user != null & user.getPassword().equals(Encoder.encode(password))) {
//            if (user != null && password.equals(user.getPassword())) {
            request.getSession().setAttribute("user", user);
        } else {
            response.setHeader("errorMSg", "Invalid Login or Password");
            request.setAttribute("errorMsg", "Invalid Login or Password");
            RequestDispatcher dispatcher = request.getRequestDispatcher(MAIN_PAGE);
            dispatcher.forward(request, response);
        }
        if(user.getRole().equals("admin")){
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/frontController?command=administrator");
        } else {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/frontController?command=userscabinet");
        }
    }
}
