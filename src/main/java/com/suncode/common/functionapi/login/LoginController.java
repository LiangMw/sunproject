package com.suncode.common.functionapi.login;

import com.suncode.common.base.BaseController;
import com.jfinal.aop.Clear;

/**
 * Created by 梁明伟 on 2019/8/16.
 * Copyright © 2018年 CETC. All rights reserved.
 * 登录逻辑接口
 */

public class LoginController extends BaseController {
    LoginService ls = new LoginService();
    @Clear
    public void index(){
        String username = getPara("username");
        if(!username.equals("")) {
            renderAppJson(ls.getUserInfo(username));
        }else{
            renderAppError("参数错误");
        }
    }

}
