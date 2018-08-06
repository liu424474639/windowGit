package com.bz.dao;

import com.bz.entity.User;

/**
 * Created by lyx on 2018/7/26.
 */
public interface UserDao {
    /*根据唯一用户名查询系统用户，如果没有则返回null*/
    public User findUserByName(String name);
}
