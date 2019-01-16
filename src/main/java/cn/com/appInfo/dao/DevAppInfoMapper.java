package cn.com.appInfo.dao;

import cn.com.appInfo.pojo.AppInfo;
import cn.com.appInfo.pojo.AppInfoExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用于开发者查询其开发的APP信息
 */
public interface DevAppInfoMapper {
    List<AppInfoExt> queryAppInfoByPage(Map<String,Object> map);
    int getTotalRecord(AppInfo appInfo);
    AppInfoExt getAppInfoById(@Param("devId")Long devId, @Param("id")Long id);
    int updateAppVersion(@Param("id")Long id,@Param("versionId") Long versionId,@Param("softwareSize") Double softwareSize);
    int checkAPKName(@Param("apkName")String apkName);
    int addAppInfo(AppInfo appInfo);
    int appOnSale(@Param("status")Long status, @Param("devId")Long devId,@Param("appId")Long appId);
    int delLogoPic(@Param("id")Long id);
    int updateAppInfo(AppInfo appInfo);
    int deleteAppInfo(@Param("id")Long id);
}
