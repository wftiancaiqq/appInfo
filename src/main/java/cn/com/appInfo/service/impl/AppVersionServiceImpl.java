package cn.com.appInfo.service.impl;

import cn.com.appInfo.dao.AppVersionMapper;
import cn.com.appInfo.pojo.AppVersion;
import cn.com.appInfo.pojo.AppVersionExt;
import cn.com.appInfo.service.AppVersionService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class AppVersionServiceImpl implements AppVersionService {
    @Resource
    private AppVersionMapper appVersionMapper;
    public AppVersionExt getAppVersionById(Long id) {
        return appVersionMapper.getAppVersionById(id);
    }

    public List<AppVersionExt> getAllAppVersionById(Long id) {
        return appVersionMapper.getAllAppVersionById(id);
    }
    //增加版本
    public int addAppVersion(MultipartFile appDownloadLink,AppVersion appVersion,String filePath) {
        String versionNo=appVersion.getVersionNo();
        AppVersion version  =appVersionMapper.checkVersionName(versionNo,appVersion.getAppId());
        if (version==null){
            //如果版本名称不存在，则继续增加版本信息的操作
            File file=new File(filePath);
            if (file.exists()){
                //获取文件名称
                String apkFileName=appDownloadLink.getOriginalFilename();
                //获取文件大小
                long fileSize=appDownloadLink.getSize();
                if (apkFileName.toLowerCase().endsWith(".apk")){
                    if (fileSize<=1024*1024*500){
                        String downloadLink="/AppInfoSystem/statics/uploadfiles/"+apkFileName;
                        String apkLocPath="F:\\workplace\\Wufeng\\appInfo\\web\\AppInfoSystem\\statics\\uploadfiles\\"+apkFileName;
                        appVersion.setApkFileName(apkFileName);
                        appVersion.setDownloadLink(downloadLink);
                        appVersion.setApkLocPath(apkLocPath);
                        //执行写入操作
                        try {
                            appDownloadLink.transferTo(new File(file,"/"+apkFileName));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        int i= appVersionMapper.addAppVersion(appVersion);
                        if (i>0){
                            //上传成功
                            return 0;
                        }else {
                            //上传失败
                            return 5;
                        }
                    }else {
                        //文件大小不符合要求
                        return 4;
                    }
                }else {
                    //app格式不符合
                    return 3;
                }
            }else {
                //文件路径不存在
                return 2;
            }

        }
        //版本名称已经存在返回1
        return 1;
    }

    public Long getNewestVersion(Long appId) {
        return appVersionMapper.getNewestVersion(appId);
    }

    public int deleteVersion(Long id) {
        return appVersionMapper.deleteVersion(id);
    }

    public int updateVersionOfAPKName(Long id) {
        return appVersionMapper.updateVersionOfAPKName(id);
    }

    public int updateVersion(MultipartFile appDownloadLink, AppVersion appVersion, String filePath) {
        File file=new File(filePath);
        if (file.exists()){
            //获取文件名称
            String apkFileName=appDownloadLink.getOriginalFilename();
            //获取文件大小
            long fileSize=appDownloadLink.getSize();
            if (apkFileName.toLowerCase().endsWith(".apk")){
                if (fileSize<=1024*1024*500){
                    String downloadLink="/AppInfoSystem/statics/uploadfiles/"+apkFileName;
                    String apkLocPath="F:\\workplace\\Wufeng\\appInfo\\web\\AppInfoSystem\\statics\\uploadfiles\\"+apkFileName;
                    appVersion.setApkFileName(apkFileName);
                    appVersion.setDownloadLink(downloadLink);
                    appVersion.setApkLocPath(apkLocPath);
                    //执行写入操作
                    try {
                        appDownloadLink.transferTo(new File(file,"/"+apkFileName));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    int i= appVersionMapper.updateVersion(appVersion);
                    if (i>0){
                        //上传成功
                        return 0;
                    }else {
                        //上传失败
                        return 4;
                    }
                }else {
                    //文件大小不符合要求
                    return 3;
                }
            }else {
                //app格式不符合
                return 2;
            }
        }
        //文件路径不存在
        return 1;
    }

    public int versionOnSale(Long publishStatus, Long devId,Long versionId) {
        return appVersionMapper.versionOnSale(publishStatus,devId,versionId);
    }

    public int deleteVersionByAppId(Long appId) {
        return appVersionMapper.deleteVersionByAppId(appId);
    }

}
