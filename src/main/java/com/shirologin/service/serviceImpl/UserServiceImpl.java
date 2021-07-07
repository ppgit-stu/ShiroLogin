package com.shirologin.service.serviceImpl;


import com.shirologin.dao.UserDao;
import com.shirologin.entity.UserBean;
import com.shirologin.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @author pengyong
 * @date 2020/5/10 - 13:06
 */
@Service
public class UserServiceImpl implements UserService {


    private UserDao userDao;

    @Resource//set方法注入依赖
    public void setUserDao(UserDao userDao) {
        this.userDao=userDao;
    }

    public UserBean login(String name, String password) {
        return userDao.getUser(name, password);
    }

    public UserBean findByName(String name) {return  userDao.findByName(name);}

    public UserBean findById(Integer id) { return userDao.findById(id); }
}
