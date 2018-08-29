package command.impl;

import entities.User;
import service.UserService;
import service.impl.UserServiceImpl;
import command.Controller;
import mappers.UserMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationController implements Controller {
    private UserService userService = UserServiceImpl.getINSTANCE();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = UserMapper.map(request);
        userService.save(user);
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/frontController?command=userscabinet");
    }
}
