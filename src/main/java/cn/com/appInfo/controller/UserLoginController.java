package cn.com.appInfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("userLoginController")
public class UserLoginController {
    @RequestMapping("/login")
    public String doLogin(){
        return "backendlogin";
    }
    @RequestMapping("/doDevLogin")
    public String doDevLogin(){
        return "devlogin";
    }
}
