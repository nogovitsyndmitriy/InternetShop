package filters;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {

    private String encoder = "utf-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encodingParameter = filterConfig.getInitParameter("encoder");
        if (encodingParameter != null && !"".equals(encodingParameter)) {
            encoder = encodingParameter;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoder);
        servletResponse.setContentType("text/html; charset=" + encoder);
        servletResponse.setCharacterEncoding(encoder);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
