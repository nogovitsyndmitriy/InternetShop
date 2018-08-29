package command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Controller {
    String MAIN_PAGE = "/WEB-INF/ui/default/default.jspx";

    void execute (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
