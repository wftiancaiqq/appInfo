package cn.com.appInfo.controller;

import cn.com.appInfo.pojo.BackendUser;
import cn.com.appInfo.service.BackendUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("backendUser")
public class BackendUserController {
    @Resource
    private BackendUserService backendUserService;
    @RequestMapping("/doLogin")
    public String doBackendUserLogin(String userCode, String userPassword, HttpServletRequest request){
        BackendUser backendUser =backendUserService.doBackendUserLogin(userCode,userPassword);
        if (backendUser!=null){
            request.getSession().setAttribute("userSession",backendUser);
            return "backend/main";
        }
        request.setAttribute("message","用户名或密码错误！");
        return "backendlogin";
    }

    @RequestMapping("/logOut")
    public String logOut(HttpServletRequest request){
        request.getSession().removeAttribute("userSession");
        request.getSession().invalidate();
        return InternalResourceViewResolver.REDIRECT_URL_PREFIX+"/index.jsp";
    }
    @RequestMapping("/main")
    public String goMain(){
        return "backend/main";
    }
}
