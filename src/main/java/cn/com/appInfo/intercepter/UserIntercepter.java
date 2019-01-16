package cn.com.appInfo.intercepter;

import cn.com.appInfo.pojo.BackendUser;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserIntercepter extends HandlerInterceptorAdapter {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws IOException {
        HttpSession session=request.getSession();
        BackendUser backendUser= (BackendUser) session.getAttribute("userSession");
       if (backendUser==null){
           response.sendRedirect(request.getContextPath()+"/403.jsp");
           return false;
       }
        return true;
    }
}
