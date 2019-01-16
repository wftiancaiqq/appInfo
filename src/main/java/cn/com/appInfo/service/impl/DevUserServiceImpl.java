package cn.com.appInfo.service.impl;

import cn.com.appInfo.dao.DevUserMapper;
import cn.com.appInfo.pojo.DevUser;
import cn.com.appInfo.service.DevUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DevUserServiceImpl implements DevUserService {
    @Resource
    private DevUserMapper devUserMapper;
    public DevUser doDevUserLogin(String devCode, String devPassword) {
        return devUserMapper.doDevUserLogin(devCode,devPassword);
    }
}
