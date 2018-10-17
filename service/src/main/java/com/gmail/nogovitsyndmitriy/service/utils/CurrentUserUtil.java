package com.gmail.nogovitsyndmitriy.service.utils;

import com.gmail.nogovitsyndmitriy.service.model.UserPrincipal;
import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
@Getter
public class CurrentUserUtil {

    public static UserPrincipal getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return userPrincipal;
    }
}
