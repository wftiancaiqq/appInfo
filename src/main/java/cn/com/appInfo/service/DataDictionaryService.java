package cn.com.appInfo.service;

import cn.com.appInfo.pojo.DataDictionary;

import java.util.List;

public interface DataDictionaryService {
    //获取平台列表
    List<DataDictionary> getFlatformList();
    //获取app状态
    List<DataDictionary> getAppStatusList();
}
