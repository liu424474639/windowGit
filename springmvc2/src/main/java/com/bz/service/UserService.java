package com.bz.service;

import com.bz.dao.UserDao;
import com.bz.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by lyx on 2018/7/26.
 */
@Service
public class UserService {
    private UserDao userDao;

    @Resource
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }
    /*登录系统功能*/
    public User login(String name, String pwd){
        try {
            User user = userDao.findUserByName(name);
            if (user.getPwd().equals(pwd)){
                return user;
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
