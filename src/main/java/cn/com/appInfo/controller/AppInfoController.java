package cn.com.appInfo.controller;

import cn.com.appInfo.pojo.*;
import cn.com.appInfo.service.AppCategoryService;
import cn.com.appInfo.service.AppInfoService;
import cn.com.appInfo.service.AppVersionService;
import cn.com.appInfo.service.DataDictionaryService;
import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("appInfoController")
public class AppInfoController {
    @Resource
    private AppInfoService appInfoService;
    @Resource
    private DataDictionaryService dataDictionaryService;
    @Resource
    private AppCategoryService appCategoryService;
    @Resource
    private AppVersionService appVersionService;
    @RequestMapping("/appInfoList")
    public String getAppInfoList(String pageIndex, AppInfo appInfo, HttpServletRequest request){
        System.out.println("nima");
        Map<String,Object> appInfoMap=appInfoService.queryAppInfoByPage(pageIndex,appInfo);
        List<DataDictionary> flatFormList=dataDictionaryService.getFlatformList();
        //将搜索的关键信息存到map中
        String querySoftwareName=appInfo.getSoftwareName();
        Long queryFlatformId=appInfo.getFlatformId();
        Long queryCategoryLevel1=appInfo.getCategoryLevel1();
        Long queryCategoryLevel2=appInfo.getCategoryLevel2();
        Long queryCategoryLevel3=appInfo.getCategoryLevel3();
        appInfoMap.put("querySoftwareName",querySoftwareName);
        appInfoMap.put("queryFlatformId",queryFlatformId);
        appInfoMap.put("queryCategoryLevel1",queryCategoryLevel1);
        appInfoMap.put("queryCategoryLevel2",queryCategoryLevel2);
        appInfoMap.put("queryCategoryLevel3",queryCategoryLevel3);
        //将搜索到的平台信息放到map中
        appInfoMap.put("flatFormList",flatFormList);
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
        return "backend/applist";
    }
    @RequestMapping(value = "/categorylevel2list",produces = "text/json;charset=utf-8")
    @ResponseBody
    public String queryCategoryLevel2(String queryCategoryLevel1){
        List<AppCategory> categoryLevel2List=appCategoryService.queryCategoryLevel2(Long.parseLong(queryCategoryLevel1));
        return JSONArray.toJSONString(categoryLevel2List);
    }
    @RequestMapping(value = "/categorylevel3list",produces = "text/json;charset=utf-8")
    @ResponseBody
    public String queryCategoryLevel3(String queryCategoryLevel2){
        List<AppCategory> categoryLevel3List=appCategoryService.queryCategoryLevel3(Long.parseLong(queryCategoryLevel2));
        return JSONArray.toJSONString(categoryLevel3List);
    }
    @RequestMapping(value = "/getCategory3ByLevel1",produces = "text/json;charset=utf-8")
    @ResponseBody
    public String getCategory3ByLevel1(String categoryLevel1){
        List<AppCategory> category3List=appCategoryService.getCategory3ByLevel1(Long.parseLong(categoryLevel1));
        return JSONArray.toJSONString(category3List);
    }
    @RequestMapping("/appcheck")
    public String appCheck(String aid,String versionid,HttpServletRequest request){
        AppInfoExt appInfoExt=appInfoService.getAppInfoById(Long.parseLong(aid));
        AppVersionExt appVersionExt=appVersionService.getAppVersionById(Long.parseLong(versionid));
        request.setAttribute("ExtAppInfo",appInfoExt);
        request.setAttribute("ExtAppVersion",appVersionExt);
        return InternalResourceViewResolver.FORWARD_URL_PREFIX+"/WEB-INF/jsp/backend/appcheck.jsp";
    }
    @RequestMapping("/checkSave")
    public String checkSave(AppInfo appInfo){
        int i=appInfoService.checkSave(appInfo);
        return InternalResourceViewResolver.REDIRECT_URL_PREFIX+"/appInfoController/appInfoList";
    }
}
