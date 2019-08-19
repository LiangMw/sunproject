package com.suncode.common.functionapi.login;


import com.suncode.common.model.bean.User;

/**
 * Created by 梁明伟 on 2019/8/16.
 * Copyright © 2018年 CETC. All rights reserved.
 */

public class LoginService {
    private static User u = new User();

    public User getUserInfo(String username){
        return u.findFirst("select * from s_user u where u.username = ?",username);
    }

}
