package cn.com.appInfo.dao;

import cn.com.appInfo.pojo.AppVersion;
import cn.com.appInfo.pojo.AppVersionExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppVersionMapper {
    AppVersionExt getAppVersionById(@Param("id") Long id);
    List<AppVersionExt> getAllAppVersionById(@Param("id") Long id);
    int addAppVersion(AppVersion appVersion);
    AppVersion checkVersionName(@Param("versionNo") String versionNo,@Param("appId")Long appId);
    Long getNewestVersion(@Param("appId") Long appId);
    int deleteVersion(@Param("id") Long id);
    int updateVersionOfAPKName(@Param("id") Long id);
    int updateVersion(AppVersion appVersion);
    int versionOnSale(@Param("publishStatus") Long publishStatus,@Param("devId")Long devId, @Param("versionId")Long versionId);
    int deleteVersionByAppId(@Param("appId")Long appId);
}
