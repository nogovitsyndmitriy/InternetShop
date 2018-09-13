package command.impl;

import command.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReduceController implements Controller {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Basket basket = (Basket) request.getSession().getAttribute("basket");
//        if (basket == null) {
//            basket = new Basket(new HashMap<>());
//            request.getSession().setAttribute("basket", basket);
//        }
//        long productId = Long.parseLong(request.getParameter("productId"));
//        AtomicInteger count = basket.getBasket().get(productId);
//        int currentCount = 0;
//        if (count == null) {
//            count = new AtomicInteger();
//            count.set(1);
//            currentCount++;
//        } else {
//            currentCount = count.decrementAndGet();
//        }
//        basket.getBasket().put(productId, count);
//        PrintWriter write = response.getWriter();
//        write.print(new Gson().toJson(currentCount));
    }
}
