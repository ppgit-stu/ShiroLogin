package com.shirologin.service;

import com.shirologin.entity.UserBean;

/**
 * @author pengyong
 * @date 2020/5/10 - 13:05
 */
public interface UserService {

    UserBean login(String name, String password);

    UserBean findByName(String name);

    UserBean findById(Integer id);
}
