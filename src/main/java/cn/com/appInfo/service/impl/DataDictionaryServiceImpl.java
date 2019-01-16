package cn.com.appInfo.service.impl;

import cn.com.appInfo.dao.DataDictionaryMapper;
import cn.com.appInfo.pojo.DataDictionary;
import cn.com.appInfo.service.DataDictionaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class DataDictionaryServiceImpl implements DataDictionaryService {
    @Resource
    private DataDictionaryMapper dataDictionaryMapper;
    public List<DataDictionary> getFlatformList() {
        return dataDictionaryMapper.getFlatformList();
    }

    public List<DataDictionary> getAppStatusList() {
        return dataDictionaryMapper.getAppStatusList();
    }
}
