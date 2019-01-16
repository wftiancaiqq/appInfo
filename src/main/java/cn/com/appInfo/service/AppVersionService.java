package cn.com.appInfo.service;

import cn.com.appInfo.pojo.AppVersion;
import cn.com.appInfo.pojo.AppVersionExt;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AppVersionService {
    AppVersionExt getAppVersionById(Long id);
    List<AppVersionExt> getAllAppVersionById(Long id);
    int addAppVersion(MultipartFile appDownloadLink, AppVersion appVersion, String filePath);
    Long getNewestVersion(Long appId);
    int deleteVersion(Long id);
    int updateVersionOfAPKName(Long id);
    int updateVersion(MultipartFile appDownloadLink, AppVersion appVersion, String filePath);
    int versionOnSale(Long publishStatus, Long devId,Long versionId);
    int deleteVersionByAppId(Long appId);
}
