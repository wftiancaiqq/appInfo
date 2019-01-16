package cn.com.appInfo.service;

import cn.com.appInfo.pojo.AppInfo;
import cn.com.appInfo.pojo.AppInfoExt;

import java.util.Map;

public interface AppInfoService {
    Map<String,Object> queryAppInfoByPage(String num,AppInfo appInfo);
    AppInfoExt getAppInfoById(Long id);
    int checkSave(AppInfo appInfo);
}
