package command.impl;

import dao.GenericCommentDao;
import dao.impl.GenericCommentDaoImpl;
import entities.*;
import service.ItemService;
import service.ProductService;
import service.impl.ItemServiceImpl;
import service.impl.ProductServiceImpl;
import command.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AddToBasketController implements Controller {
    ProductService productService = ProductServiceImpl.getINSTANCE();
    ItemService itemService = ItemServiceImpl.getINSTANCE();
    GenericCommentDao genericCommentDao = new GenericCommentDaoImpl(Comment.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Basket basket = (Basket) request.getSession().getAttribute("basket");
//        if (basket == null) {
//            basket = new Basket(new LinkedList<>());
//            request.getSession().setAttribute("basket", basket);
//        }

        User user = (User) request.getSession().getAttribute("user");
        if (user  == null ||"".equals(user)) {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/frontController?command=login");
        } else {
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            long productId = Long.parseLong(request.getParameter("productId"));
            Product product = productService.get(productId);
            Order order = new Order();
            Item item = itemService.save(new Item(product.getName(), quantity, order.getId(),productId, user.getId(), product.getPrice()));
//        basket.getBasket().add(item);

            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/frontController?command=products");
        }
    }
}
