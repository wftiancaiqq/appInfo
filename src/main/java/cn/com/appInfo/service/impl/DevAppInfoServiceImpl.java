package cn.com.appInfo.service.impl;

import cn.com.appInfo.dao.DevAppInfoMapper;
import cn.com.appInfo.pojo.AppInfo;
import cn.com.appInfo.pojo.AppInfoExt;
import cn.com.appInfo.service.DevAppInfoService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DevAppInfoServiceImpl implements DevAppInfoService {
    @Resource
    private DevAppInfoMapper devAppInfoMapper;

    public Map<String, Object> queryAppInfoByPage(String num, AppInfo appInfo) {
        Map<String,Object> map=new HashMap<String, Object>();
        int pageSize=5;
        //默认首次访问首页
        int pageNumber=1;
        if (num!=null && num.length()>0){
            pageNumber=Integer.parseInt(num);
        }
        int pageRecord=devAppInfoMapper.getTotalRecord(appInfo);
        //计算共有多少页
        int totalPage=pageRecord%pageSize==0?pageRecord/pageSize:pageRecord/pageSize+1;
        //求当前页的内容
        int pageIndex=(pageNumber-1)*pageSize;
        Map<String,Object> mapUnion=new HashMap<String, Object>();
        mapUnion.put("pageIndex",pageIndex);
        mapUnion.put("pageSize",pageSize);
        mapUnion.put("appInfo",appInfo);
        List<AppInfoExt> appInfoList=devAppInfoMapper.queryAppInfoByPage(mapUnion);
        map.put("totalRecord",pageRecord);
        map.put("pageNumber",pageNumber);
        map.put("totalPage",totalPage);
        map.put("appInfoList",appInfoList);
        return map;
    }

    public AppInfoExt getAppInfoById(Long devId, Long id) {
        return devAppInfoMapper.getAppInfoById(devId,id);
    }

    public int updateAppVersion(Long id, Long versionId,Double softwareSize) {
        return devAppInfoMapper.updateAppVersion(id,versionId,softwareSize);
    }

    public int checkAPKName(String apkName) {
        return devAppInfoMapper.checkAPKName(apkName);
    }

    public int addAppInfo(MultipartFile a_logoPicPath, AppInfo appInfo,String filePath) {
        File file=new File(filePath);
        if (file.exists()){
            //获取文件名称
            String logoPicName =a_logoPicPath.getOriginalFilename();
            //获取文件大小
            long fileSize=a_logoPicPath.getSize();
            if (logoPicName.toLowerCase().endsWith(".gif")||
                    logoPicName.toLowerCase().endsWith(".bmp")||
                    logoPicName.toLowerCase().endsWith(".jpeg")||
                    logoPicName.toLowerCase().endsWith(".icon")||
                    logoPicName.toLowerCase().endsWith(".jpg")){
                if (fileSize<=1024*50){
                    String logoPicPath="/AppInfoSystem/statics/uploadfiles/"+logoPicName;
                    String logoLocPath="F:\\workplace\\Wufeng\\appInfo\\web\\AppInfoSystem\\statics\\uploadfiles\\"+logoPicName;
                    appInfo.setLogoPicPath(logoPicPath);
                    appInfo.setLogoLocPath(logoLocPath);
                    //执行写入操作
                    try {
                        a_logoPicPath.transferTo(new File(file,"/"+logoPicName));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    int i= devAppInfoMapper.addAppInfo(appInfo);
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

    public int appOnSale(Long status, Long devId, Long appId) {
        return devAppInfoMapper.appOnSale(status,devId,appId);
    }

    public int delLogoPic(Long id) {
        return devAppInfoMapper.delLogoPic(id);
    }

    public int updateAppInfo(MultipartFile a_logoPicPath, AppInfo appInfo, String filePath) {
        File file=new File(filePath);
        if (file.exists()){
            //获取文件名称
            String logoPicName =a_logoPicPath.getOriginalFilename();
            //获取文件大小
            long fileSize=a_logoPicPath.getSize();
            if (logoPicName.toLowerCase().endsWith(".gif")||
                    logoPicName.toLowerCase().endsWith(".bmp")||
                    logoPicName.toLowerCase().endsWith(".jpeg")||
                    logoPicName.toLowerCase().endsWith(".icon")||
                    logoPicName.toLowerCase().endsWith(".jpg")){
                if (fileSize<=1024*50){
                    String logoPicPath="/AppInfoSystem/statics/uploadfiles/"+logoPicName;
                    String logoLocPath="F:\\workplace\\Wufeng\\appInfo\\web\\AppInfoSystem\\statics\\uploadfiles\\"+logoPicName;
                    appInfo.setLogoPicPath(logoPicPath);
                    appInfo.setLogoLocPath(logoLocPath);
                    //执行写入操作
                    try {
                        a_logoPicPath.transferTo(new File(file,"/"+logoPicName));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    int i= devAppInfoMapper.updateAppInfo(appInfo);
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

    public int deleteAppInfo(Long id) {
        return devAppInfoMapper.deleteAppInfo(id);
    }
}
