package cn.com.appInfo.dao;

import cn.com.appInfo.pojo.BackendUser;
import org.apache.ibatis.annotations.Param;

/**
 * created by Jason on 2019/1/12
 * 后台管理员pojo类
 */
public interface BackendUserMapper {
    /**
     * 处理后台管理员登录
     * @param userCode
     * @param userPassword
     * @return BackendUser对象
     */
    BackendUser doBackendUserLogin(@Param("userCode") String userCode,@Param("userPassword")  String userPassword);

    /**
     * 查询后台管理员信息
     * @param id
     * @return
     */
    BackendUser getUserPersonalInfo(Long id);
}
