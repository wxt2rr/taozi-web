package filter;

import manage.ConstantManage;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class UserUnionIdFilter implements Filter{

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain)throws IOException,ServletException {
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        try {
            request = (HttpServletRequest) req;
            response = (HttpServletResponse) rep;
        } catch (Exception e) {
            return;
        }
        addUnionId(request, response);
        chain.doFilter(request, response);
    }

    /**
     * Description:为临时用户设置唯一标识
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    private void addUnionId(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        boolean isSet = cookies == null;
        if(!isSet){
            for(Cookie c : cookies){
                if(ConstantManage.USER_UNION_ID.equals(c.getName())){
                   isSet = false;
                   break;
                }
            }
        }
        if(isSet){
            String val = String.format("%s_%s",UUID.randomUUID(),UUID.randomUUID());
            Cookie cookie = new Cookie(ConstantManage.USER_UNION_ID,val);
            cookie.setMaxAge(30 * 24 * 60 * 60);
            response.addCookie(cookie);
            request.setAttribute(ConstantManage.USER_UNION_ID,val);
        }
    }

    public void destroy() {
    }
}
