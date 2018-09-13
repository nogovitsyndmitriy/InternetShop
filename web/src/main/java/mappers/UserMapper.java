package mappers;

import auth.Encoder;

import javax.servlet.http.HttpServletRequest;

public class UserMapper {

    public static User map(HttpServletRequest request) {
        User user = new User();
        user.setLogin(request.getParameter("login"));
        user.setPassword(Encoder.encode(request.getParameter("password")));
        user.setRole(request.getParameter("role"));
        user.setName(request.getParameter("name"));
        user.setLastName(request.getParameter("lastname"));
        user.setAge(request.getParameter("age"));
        user.setEmail(request.getParameter("email"));
        return user;
    }
}
