package cn.com.appInfo.dao;

import cn.com.appInfo.pojo.DataDictionary;

import java.util.List;

public interface DataDictionaryMapper {
    //获取平台列表
    List<DataDictionary> getFlatformList();
    //获取app状态
    List<DataDictionary> getAppStatusList();
}
