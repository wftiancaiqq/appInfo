package cn.com.appInfo.controller;

import cn.com.appInfo.pojo.*;
import cn.com.appInfo.service.*;
import com.alibaba.fastjson.JSONArray;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
    @RequestMapping("appInfoDevController")
public class AppInfoDevController {
    @Resource
    private DevAppInfoService devAppInfoService;
    @Resource
    private DataDictionaryService dataDictionaryService;
    @Resource
    private AppCategoryService appCategoryService;
    @Resource
    private AppVersionService appVersionService;
    @RequestMapping("/appInfoList")
    public String getAppInfoList(String pageIndex, AppInfo appInfo, HttpServletRequest request){
        DevUser devUser = (DevUser) request.getSession().getAttribute("devUserSession");
        appInfo.setDevId(devUser.getId()!=null?devUser.getId():0L);
        Map<String,Object> appInfoMap=devAppInfoService.queryAppInfoByPage(pageIndex,appInfo);
        List<DataDictionary> flatFormList=dataDictionaryService.getFlatformList();
        List<DataDictionary> statusList=dataDictionaryService.getAppStatusList();
        //将搜索的关键信息存到map中
        String querySoftwareName=appInfo.getSoftwareName();
        Long queryFlatformId=appInfo.getFlatformId();
        Long queryStatus=appInfo.getStatus();
        Long queryCategoryLevel1=appInfo.getCategoryLevel1();
        Long queryCategoryLevel2=appInfo.getCategoryLevel2();
        Long queryCategoryLevel3=appInfo.getCategoryLevel3();
        appInfoMap.put("querySoftwareName",querySoftwareName);
        appInfoMap.put("queryFlatformId",queryFlatformId);
        appInfoMap.put("queryStatus",queryStatus);
        appInfoMap.put("queryCategoryLevel1",queryCategoryLevel1);
        appInfoMap.put("queryCategoryLevel2",queryCategoryLevel2);
        appInfoMap.put("queryCategoryLevel3",queryCategoryLevel3);
        //将搜索到的平台信息放到map中
        appInfoMap.put("flatFormList",flatFormList);
        //将搜索到的app状态信息放到map中
        appInfoMap.put("statusList",statusList);
        //将一级分类放在map中
        List<AppCategory> categoryLevel1List=appCategoryService.queryCategoryLevel1();
        appInfoMap.put("categoryLevel1List",categoryLevel1List);
        //将二级分类放在map中
        List<AppCategory> categoryLevel2List=appCategoryService.queryCategoryLevel2(appInfo.getCategoryLevel1());
        appInfoMap.put("categoryLevel2List",categoryLevel2List);
        //将三级分类放在map中
        List<AppCategory> categoryLevel3List=appCategoryService.queryCategoryLevel3(appInfo.getCategoryLevel2());
        appInfoMap.put("categoryLevel3List",categoryLevel3List);

        request.getSession().setAttribute("appInfoMap",appInfoMap);
        return "developer/appinfolist";
    }
    @RequestMapping(value = "/categoryLevel",produces = "text/json;charset=utf-8")
    @ResponseBody
    public String queryCategoryLevel(String parentId){
        List<AppCategory> categoryLevelList=appCategoryService.queryCategoryLevel(Long.parseLong(parentId));
        return JSONArray.toJSONString(categoryLevelList);
    }
    @RequestMapping("/appversionadd")
    public String appVersionAdd(@Param("id")String id, HttpServletRequest request){
        List<AppVersionExt> appVersionExtList=appVersionService.getAllAppVersionById(Long.parseLong(id));
        request.getSession().setAttribute("appInfoId",id);
        request.getSession().setAttribute("appVersion",appVersionExtList);
        return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appversionadd.jsp";
    }
    @RequestMapping("/appversionaddInfo")
    public String appversionaddInfo(MultipartFile appDownloadLink , AppVersion appVersion, HttpServletRequest request){
        DevUser devUser = (DevUser) request.getSession().getAttribute("devUserSession");
        appVersion.setCreatedBy(devUser.getId());
        String filePath=request.getRealPath("AppInfoSystem/statics/uploadfiles");
        int i=appVersionService.addAppVersion(appDownloadLink,appVersion,filePath);
        if (i==1){
            //版本名称已经存在
            request.setAttribute("fileUploadError","版本名称已经存在");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appversionadd.jsp";
        } else if (i==2){
            //文件路径不存在
            request.setAttribute("fileUploadError","文件路径不存在");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appversionadd.jsp";

        } else if (i==3){
            //app格式不符合
            request.setAttribute("fileUploadError","文件不是apk格式");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appversionadd.jsp";

        } else if (i==4){
            //文件超过规定大小
            request.setAttribute("fileUploadError","文件超过规定大小");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appversionadd.jsp";
        } else if (i==5){
            //上传失败
            request.setAttribute("fileUploadError","上传版本失败");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appversionadd.jsp";
        }
        //将上传的最新app版本ID赋予app信息表
        Long versionId=appVersionService.getNewestVersion(appVersion.getAppId());
        devAppInfoService.updateAppVersion(appVersion.getAppId(),versionId,appVersion.getVersionSize());
        return InternalResourceViewResolver.REDIRECT_URL_PREFIX+"/appInfoDevController/appInfoList";
    }
    @RequestMapping("/appview")
    public String goToAppView(@Param("aid")String aid,@Param("vid")String vid, HttpServletRequest request){
        DevUser devUser = (DevUser) request.getSession().getAttribute("devUserSession");
        List<AppVersionExt> appVersionExtList=appVersionService.getAllAppVersionById(Long.parseLong(aid));
        AppInfoExt appInfoExt=devAppInfoService.getAppInfoById(devUser.getId(),Long.parseLong(aid));
        request.setAttribute("appVersionList",appVersionExtList);
        request.setAttribute("appInfo",appInfoExt);
        return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appinfoview.jsp";
    }
    @RequestMapping("/modifyVersion")
    public String goToModifyVersion(@Param("versionId")String versionId,@Param("appInfoId")String appInfoId, HttpServletRequest request){
        List<AppVersionExt> appVersionExtList=appVersionService.getAllAppVersionById(Long.parseLong(appInfoId));
        AppVersionExt appVersionExt=appVersionService.getAppVersionById(Long.parseLong(versionId));
        request.getSession().setAttribute("appInfoId",appInfoId);
        request.getSession().setAttribute("appVersion",appVersionExtList);
        request.getSession().setAttribute("appVersionExt",appVersionExt);
        return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appversionmodify.jsp";
    }
    @RequestMapping(value = "/delfile",produces = "text/json;charset=utf-8")
    @ResponseBody
    public String delFile(@Param("vid") String vid){
        int i=appVersionService.updateVersionOfAPKName(Long.parseLong(vid));
        if (i==1){
            return "success";
        }
        return "failed";
    }
    @RequestMapping("/modifyVersionInfo")
    public String modifyVersionInfo(@RequestParam(value = "attach") MultipartFile appDownloadLink, AppVersion appVersion, HttpServletRequest request){
        //String name=appDownloadLink.getOriginalFilename();
        String filePath=request.getRealPath("AppInfoSystem/statics/uploadfiles");
        int i=appVersionService.updateVersion(appDownloadLink,appVersion,filePath);
        if (i==1){
            //文件路径不存在
            request.setAttribute("fileUploadError","文件路径不存在");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appversionmodify.jsp";

        } else if (i==2){
            //app格式不符合
            request.setAttribute("fileUploadError","文件不是apk格式");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appversionmodify.jsp";

        } else if (i==3){
            //文件超过规定大小
            request.setAttribute("fileUploadError","文件超过规定大小");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appversionmodify.jsp";
        } else if (i==4){
            //上传失败
            request.setAttribute("fileUploadError","上传版本失败");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appversionmodify.jsp";
        }
        //将上传的最新app版本ID赋予app信息表
        Long versionId=appVersionService.getNewestVersion(appVersion.getAppId());
        devAppInfoService.updateAppVersion(appVersion.getAppId(),versionId,appVersion.getVersionSize());
        return InternalResourceViewResolver.REDIRECT_URL_PREFIX+"/appInfoDevController/appInfoList";
    }
    @RequestMapping("/goToAddApp")
    public String goToAddApp(HttpServletRequest request){
        Map<String,Object> map=new HashMap<String, Object>();
        //将所属平台放到map中
        List<DataDictionary> flatFormList=dataDictionaryService.getFlatformList();
        map.put("flatFormList",flatFormList);
        //将一级分类放在map中
        List<AppCategory> categoryLevel1List=appCategoryService.queryCategoryLevel1();
        map.put("categoryLevel1List", categoryLevel1List);
        //将二级分类放在map中
        List<AppCategory> categoryLevel2List=appCategoryService.queryCategoryLevel2(0L);
        map.put("categoryLevel2List",categoryLevel2List);
        //将三级分类放在map中
        List<AppCategory> categoryLevel3List=appCategoryService.queryCategoryLevel3(0L);
        map.put("categoryLevel3List",categoryLevel3List);
        request.getSession().setAttribute("map",map);
        return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appinfoadd.jsp";
    }
    @RequestMapping(value = "/jsonAPKName",produces = "text/json;charset=utf-8")
    @ResponseBody
    public String checkAPKNameByJson(String apkName, HttpServletRequest request){
        if (apkName==null || "".equals(apkName)){
            return "empty";
        }else {
            int i=devAppInfoService.checkAPKName(apkName);
            if (i>0){
                return "exist";
            }
        }
        return "noexist";
    }

    @RequestMapping("/addAppInfo")
    public String addAppInfo(@RequestParam("a_logoPicPath") MultipartFile a_logoPicPath, AppInfo appInfo,HttpServletRequest request){
        if (appInfo.getCategoryLevel1()==0 || appInfo.getCategoryLevel2()==0 || appInfo.getCategoryLevel3()==0){
            request.setAttribute("fileUploadError","所属分类不能为空！");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appinfoadd.jsp";
        }
        DevUser devUser= (DevUser) request.getSession().getAttribute("devUserSession");
        appInfo.setDevId(devUser.getId());
        appInfo.setCreatedBy(devUser.getId());
        String filePath=request.getRealPath("AppInfoSystem/statics/uploadfiles");
        int i=devAppInfoService.addAppInfo(a_logoPicPath,appInfo,filePath);
        if (i==1){
            //文件路径不存在
            request.setAttribute("fileUploadError","文件路径不存在");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appinfoadd.jsp";
        } else if (i==2){
            //app格式不符合
            request.setAttribute("fileUploadError","文件类型非要求的图像格式");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appinfoadd.jsp";

        } else if (i==3){
            //文件超过规定大小
            request.setAttribute("fileUploadError","文件超过规定大小");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appinfoadd.jsp";
        } else if (i==4){
            //上传失败
            request.setAttribute("fileUploadError","上传logo图片失败");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appinfoadd.jsp";
        }
        return InternalResourceViewResolver.REDIRECT_URL_PREFIX+"/appInfoDevController/appInfoList";
    }
    @RequestMapping(value = "/sale",produces = "text/json;charset=utf-8")
    @ResponseBody
    public String onSale(String appId,HttpServletRequest request){
        DevUser devUser= (DevUser) request.getSession().getAttribute("devUserSession");
        AppInfoExt appInfoExt=devAppInfoService.getAppInfoById(devUser.getId(),Long.parseLong(appId));
        Long versionId=appVersionService.getNewestVersion(Long.parseLong(appId));
        //注：上架和下架同个方法
        if (appInfoExt.getStatus()==2){
            //上架APP
            int result1=devAppInfoService.appOnSale(4L,devUser.getId(),Long.parseLong(appId));
            //上架app的最新版本
            int result2=appVersionService.versionOnSale(2L,devUser.getId(),versionId);
            if (result1>0 && result2>0){
                return "success";
            }
        }else if (appInfoExt.getStatus()==4){
            //下架APP
            int result1=devAppInfoService.appOnSale(5L,devUser.getId(),Long.parseLong(appId));
            //下架app的最新版本
            int result2=appVersionService.versionOnSale(3L,devUser.getId(),versionId);
            if (result1>0 && result2>0){
                return "success";
            }
        }
        return "failed";
    }
    @RequestMapping("/modify")
    public String modifyAppInfo(@Param("aid")String aid,HttpServletRequest request){
        DevUser devUser = (DevUser) request.getSession().getAttribute("devUserSession");
        //List<AppVersionExt> appVersionExtList=appVersionService.getAllAppVersionById(Long.parseLong(aid));
        AppInfoExt appInfoExt=devAppInfoService.getAppInfoById(devUser.getId(),Long.parseLong(aid));
       /* request.setAttribute("appVersionList",appVersionExtList);*/
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("appInfo",appInfoExt);
        //将所属平台放到map中
        List<DataDictionary> flatFormList=dataDictionaryService.getFlatformList();
        map.put("flatFormList",flatFormList);
        //将一级分类放在map中
        List<AppCategory> categoryLevel1List=appCategoryService.queryCategoryLevel1();
        map.put("categoryLevel1List", categoryLevel1List);
        //将二级分类放在map中
        List<AppCategory> categoryLevel2List=appCategoryService.queryCategoryLevel2(0L);
        map.put("categoryLevel2List",categoryLevel2List);
        //将三级分类放在map中
        List<AppCategory> categoryLevel3List=appCategoryService.queryCategoryLevel3(0L);
        map.put("categoryLevel3List",categoryLevel3List);
        request.getSession().setAttribute("map",map);
        return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appinfomodify.jsp";
    }
    @RequestMapping(value = "/delImage",produces = "text/json;charset=utf-8")
    @ResponseBody
    public String deleteImageOfApp(@Param("id")String id){
        int i=devAppInfoService.delLogoPic(Long.parseLong(id));
        if (i>0){
            return "success";
        }
        return "failed";
    }
    @RequestMapping("/modifyInfo")
    public String modifyAppInfo(@RequestParam("attach")MultipartFile a_logoPicPath, AppInfo appInfo,HttpServletRequest request){
        if (appInfo.getCategoryLevel1()==0){
            appInfo.setCategoryLevel1(null);
        }else if (appInfo.getCategoryLevel2()==0){
            appInfo.setCategoryLevel2(null);
        }else if (appInfo.getCategoryLevel3()==0){
            appInfo.setCategoryLevel3(null);
        }
        DevUser devUser= (DevUser) request.getSession().getAttribute("devUserSession");
        appInfo.setModifyBy(devUser.getId());
        String filePath=request.getRealPath("AppInfoSystem/statics/uploadfiles");
        int i=devAppInfoService.updateAppInfo(a_logoPicPath,appInfo,filePath);
        if (i==1){
            //文件路径不存在
            request.setAttribute("fileUploadError","文件路径不存在");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appinfomodify.jsp";
        } else if (i==2){
            //app格式不符合
            request.setAttribute("fileUploadError","文件类型非要求的图像格式");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appinfomodify.jsp";

        } else if (i==3){
            //文件超过规定大小
            request.setAttribute("fileUploadError","文件超过规定大小");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appinfomodify.jsp";
        } else if (i==4){
            //上传失败
            request.setAttribute("fileUploadError","上传logo图片失败");
            return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/developer/appinfomodify.jsp";
        }
        return InternalResourceViewResolver.REDIRECT_URL_PREFIX+"/appInfoDevController/appInfoList";
    }
    @RequestMapping(value = "/delAppInfo",produces = "text/json;charset=utf-8")
    @ResponseBody
    public String deleteAppInfo(@Param("id")String id){
        devAppInfoService.deleteAppInfo(Long.parseLong(id));
        appVersionService.deleteVersionByAppId(Long.parseLong(id));
        return "true";
    }
}
