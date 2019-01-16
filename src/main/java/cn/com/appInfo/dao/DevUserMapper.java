package cn.com.appInfo.dao;

import cn.com.appInfo.pojo.DevUser;
import org.apache.ibatis.annotations.Param;

public interface DevUserMapper {
    DevUser doDevUserLogin(@Param("devCode")String devCode,@Param("devPassword")String devPassword);
}
