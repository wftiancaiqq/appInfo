package cn.com.appInfo.service;

import cn.com.appInfo.pojo.DevUser;

public interface DevUserService {
    DevUser doDevUserLogin(String devCode,String devPassword);
}
