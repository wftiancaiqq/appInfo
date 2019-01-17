package cn.com.appInfo.service.impl;

import cn.com.appInfo.dao.AppCategoryMapper;
import cn.com.appInfo.pojo.AppCategory;
import cn.com.appInfo.service.AppCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AppCategoryServiceImpl implements AppCategoryService {
    @Resource
    private AppCategoryMapper appCategoryMapper;

    public List<AppCategory> queryCategoryLevel1() {
        return appCategoryMapper.queryCategoryLevel1();
    }

    public List<AppCategory> queryCategoryLevel2(Long parentId) {
        return appCategoryMapper.queryCategoryLevel2(parentId);
    }

    public List<AppCategory> queryCategoryLevel3(Long parentId) {
        return appCategoryMapper.queryCategoryLevel3(parentId);
    }

    public List<AppCategory> queryCategoryLevel(Long parentId) {
        return appCategoryMapper.queryCategoryLevel(parentId);
    }

    public List<AppCategory> getCategory3ByLevel1(Long parentId) {
        return appCategoryMapper.getCategory3ByLevel1(parentId);
    }
}
