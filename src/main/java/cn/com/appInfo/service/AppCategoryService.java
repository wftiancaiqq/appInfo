package cn.com.appInfo.service;

import cn.com.appInfo.pojo.AppCategory;

import java.util.List;

public interface AppCategoryService {
    List<AppCategory> queryCategoryLevel1();
    List<AppCategory> queryCategoryLevel2(Long parentId);
    List<AppCategory> queryCategoryLevel3(Long parentId);
    List<AppCategory> queryCategoryLevel(Long parentId);
}
