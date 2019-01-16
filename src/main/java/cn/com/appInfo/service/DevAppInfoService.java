package cn.com.appInfo.service;

import cn.com.appInfo.pojo.AppInfo;
import cn.com.appInfo.pojo.AppInfoExt;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface DevAppInfoService {
    Map<String,Object> queryAppInfoByPage(String num,AppInfo appInfo);
    AppInfoExt getAppInfoById(Long devId, Long id);
    int updateAppVersion(Long id, Long versionId,Double softwareSize);
    int checkAPKName(String apkName);
    int addAppInfo(MultipartFile a_logoPicPath, AppInfo appInfo,String filePath);
    int appOnSale(Long status, Long devId, Long appId);
    int delLogoPic(Long id);
    int updateAppInfo(MultipartFile a_logoPicPath, AppInfo appInfo,String filePath);
    int deleteAppInfo(Long id);
}
