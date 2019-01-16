package cn.com.appInfo.controller;

import cn.com.appInfo.pojo.DevUser;
import cn.com.appInfo.service.DevUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("devUserController")
public class DevUserController {
    @Resource
    private DevUserService devUserService;
    @RequestMapping("/login")
    public String doLogin(){
        return "devlogin";
    }
    @RequestMapping("/doLogin")
    public String doDevUserLogin(String devCode, String devPassword, HttpServletRequest request){
        DevUser devUser=devUserService.doDevUserLogin(devCode,devPassword);
        if (devUser!=null){
            request.getSession().setAttribute("devUserSession",devUser);
            return "developer/main";
        }
        request.setAttribute("message","用户名或密码错误！");
        return "devlogin";
    }
    @RequestMapping("/goDevUserMain")
    public String goDevUserMain(){
        return "developer/main";
    }
    @RequestMapping("/logOut")
    public String logOut(HttpServletRequest request){
        request.getSession().removeAttribute("devUserSession");
        request.getSession().invalidate();
        return InternalResourceViewResolver.REDIRECT_URL_PREFIX+"/index.jsp";
    }
}
