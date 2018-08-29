package command.impl;

import service.ProductService;
import service.impl.ProductServiceImpl;
import command.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductsController implements Controller {
    private ProductService productService = ProductServiceImpl.getINSTANCE();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("product", productService.getAll());
        request.setAttribute("product", productService.getAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher(MAIN_PAGE);
        dispatcher.forward(request,response);

    }
}
