package cn.com.appInfo.dao;

import cn.com.appInfo.pojo.AppCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppCategoryMapper {
    List<AppCategory> queryCategoryLevel1();
    List<AppCategory> queryCategoryLevel2(@Param("parentId") Long parentId);
    List<AppCategory> queryCategoryLevel3(@Param("parentId") Long parentId);
    List<AppCategory> queryCategoryLevel(@Param("parentId")Long parentId);
    List<AppCategory> getCategory3ByLevel1(@Param("parentId")Long parentId);
}
