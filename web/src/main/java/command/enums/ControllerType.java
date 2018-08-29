package command.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import command.Controller;
import command.impl.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ControllerType {

    HOME("home.jsp", "Home", new HomeController()),
    CREATE_ORDER("", "createOrder", new CreateOrderController()),
    PRODUCTS("products.jsp", "Products", new ProductsController()),
    REGISTRATION("registration.jsp", "Registration", new RegistrationController()),
    REGPAGE("registration.jsp", "RegistrationPage", new RegPageController()),
    USERSCABINET("userscabinet.jsp", "Userscabinet", new UsersCabinetController()),
    FAQ("faq.jsp", "FAQ", new FaqController()),
    ADMINISTRATOR("administrator.jsp", "Administrator", new AdministratorController()),
    BASKET("basket.jsp", "Basket", new BasketController()),
    ADD_TO_BASKET("", "addToBasket", new AddToBasketController()),
    REDUCE_BASKET("","reduceBasket", new ReduceController()),
    SEARCHRESULTS("searchresults.jsp", "Searchresults", new SearchController()),
    LOGIN("login.jsp", "Login", new LoginController()),
    LOGOUT("home.jsp", "Logout", new LogOutController());

    private String pagePath;
    private String pageName;
    private Controller controller;

    public static ControllerType getByPageName(String page) {
        for (ControllerType type : ControllerType.values()) {
            if (type.pageName.equalsIgnoreCase(page)) {
                return type;
            }
        }
        return HOME;
    }
}
