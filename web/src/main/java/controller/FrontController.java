package controller;

import command.enums.ControllerType;
import handler.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(urlPatterns = "/frontController")
public class FrontController extends HttpServlet {
    private RequestHandler requestHandler = new RequestHandler();


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ControllerType controllerType = RequestHandler.getCommand(req);
        controllerType.getController().execute(req, resp);

    }
}
