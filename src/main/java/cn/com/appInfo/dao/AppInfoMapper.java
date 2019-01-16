package cn.com.appInfo.dao;


import cn.com.appInfo.pojo.AppInfo;
import cn.com.appInfo.pojo.AppInfoExt;

import java.util.List;
import java.util.Map;

public interface AppInfoMapper {
    List<AppInfoExt> queryAppInfoByPage(Map<String,Object> map);
    int getTotalRecord(AppInfo appInfo);
    AppInfoExt getAppInfoById(Long id);
    int checkSave(AppInfo appInfo);
}
