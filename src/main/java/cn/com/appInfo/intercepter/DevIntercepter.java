package cn.com.appInfo.intercepter;

import cn.com.appInfo.pojo.DevUser;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DevIntercepter extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        HttpSession session=request.getSession();
        DevUser devUser = (DevUser) session.getAttribute("devUserSession");
        if (devUser==null){
            response.sendRedirect(request.getContextPath()+"/403.jsp");
            return false;
        }
        return true;
    }
}
