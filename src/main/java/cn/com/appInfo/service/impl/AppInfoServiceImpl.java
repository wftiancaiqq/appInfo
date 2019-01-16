package cn.com.appInfo.service.impl;

import cn.com.appInfo.dao.AppInfoMapper;
import cn.com.appInfo.pojo.AppInfo;
import cn.com.appInfo.pojo.AppInfoExt;
import cn.com.appInfo.service.AppInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class AppInfoServiceImpl implements AppInfoService {
    @Resource
    private AppInfoMapper appInfoMapper;
    public Map<String, Object> queryAppInfoByPage(String num, AppInfo appInfo) {
        Map<String,Object> map=new HashMap<String, Object>();
        int pageSize=5;
        //默认首次访问首页
        int pageNumber=1;
        if (num!=null && num.length()>0){
            pageNumber=Integer.parseInt(num);
        }
        int pageRecord=appInfoMapper.getTotalRecord(appInfo);
        //计算共有多少页
        int totalPage=pageRecord%pageSize==0?pageRecord/pageSize:pageRecord/pageSize+1;
        //求当前页的内容
        int pageIndex=(pageNumber-1)*pageSize;
        Map<String,Object> mapUnion=new HashMap<String, Object>();
        mapUnion.put("pageIndex",pageIndex);
        mapUnion.put("pageSize",pageSize);
        mapUnion.put("appInfo",appInfo);
        List<AppInfoExt> appInfoList=appInfoMapper.queryAppInfoByPage(mapUnion);
        /*String softwareName=appInfo.getSoftwareName()!=null&&appInfo.getSoftwareName().length()>0?appInfo.getSoftwareName():"";
        Long flatformId=appInfo.getFlatformId()!=null?appInfo.getFlatformId():0;
        Long categoryLevel1=appInfo.getCategoryLevel1()!=null?appInfo.getCategoryLevel1():0;
        Long categoryLevel2=appInfo.getCategoryLevel2()!=null?appInfo.getCategoryLevel2():0;
        Long categoryLevel3=appInfo.getCategoryLevel3()!=null?appInfo.getCategoryLevel3():0;
        Long devId=appInfo.getDevId()!=null?appInfo.getDevId():0;
        String ServletUrl="/appInfoController/appInfoList?softwareName="+softwareName+"&flatformId="+flatformId+
                "&categoryLevel1="+categoryLevel1+"&categoryLevel2="+categoryLevel2+"&categoryLevel3="+categoryLevel3+
                "&devId"+devId;
        map.put("ServletUrl",ServletUrl);*/
        map.put("totalRecord",pageRecord);
        map.put("pageNumber",pageNumber);
        map.put("totalPage",totalPage);
        map.put("appInfoList",appInfoList);
        return map;
    }

    public AppInfoExt getAppInfoById(Long id) {
        return appInfoMapper.getAppInfoById(id);
    }

    public int checkSave(AppInfo appInfo) {
        return appInfoMapper.checkSave(appInfo);
    }
}
