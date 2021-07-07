package com.shirologin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shirologin.entity.UserBean;
import org.apache.ibatis.annotations.Param;
/**
 * @author pengyong
 * @date 2020/5/10 - 13:05
 */
public interface UserDao extends BaseMapper<UserBean> {

    UserBean getUser(@Param("name") String name,@Param("password") String password);
    UserBean findByName(@Param("name") String name);
    UserBean findById(@Param("value") Integer id);
}
