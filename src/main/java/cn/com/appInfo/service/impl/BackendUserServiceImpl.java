package cn.com.appInfo.service.impl;

import cn.com.appInfo.dao.BackendUserMapper;
import cn.com.appInfo.pojo.BackendUser;
import cn.com.appInfo.service.BackendUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BackendUserServiceImpl implements BackendUserService {
    @Resource
    private BackendUserMapper backendUserMapper;

    public BackendUser doBackendUserLogin(String userCode, String userPassword) {
        BackendUser backendUser=backendUserMapper.doBackendUserLogin(userCode,userPassword);
        return backendUser;
    }

    public BackendUser getUserPersonalInfo(Long id) {
        return backendUserMapper.getUserPersonalInfo(id);
    }
}
