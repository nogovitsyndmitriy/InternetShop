package com.gmail.nogovitsyndmitriy.handlers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@Component
public class AppSuccessHandler implements AuthenticationSuccessHandler {
    private final static Logger log = LogManager.getLogger(AppSuccessHandler.class);
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
handle(httpServletRequest, httpServletResponse, authentication);
clearAuthenticationAttributes(httpServletRequest);
    }

    private void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted()) {
            log.info("response has already been committed. Unable to redirect to{}", targetUrl);
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    private String determineTargetUrl(Authentication authentication) {
        boolean isUser = false;
        boolean isAdmin = false;
        boolean isSaleUser = false;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("VIEW_DOCUMENTS")) {
                isUser = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("VIEW_USERS")) {
                isAdmin = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("UPLOAD_ITEM")){
                isSaleUser = true;
                break;
            }
        }
        if (isUser) {
            return "/web/items";
        } else if (isAdmin) {
            return "/web/users";
        } else if (isSaleUser){
            return "/web/items/manage_items";
        }else {
            throw new IllegalStateException();
        }
    }

    private void clearAuthenticationAttributes(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null){
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
