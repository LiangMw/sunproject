package com.suncode.common.functionapi.login;

import com.jfinal.aop.Clear;
import com.jfinal.plugin.activerecord.Record;
import com.suncode.common.base.BaseController;

import java.util.List;

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

    @Clear
    public void getTableRow(){

        List<Record> tb = ls.getTables();
        for (int i = 0; i < tb.size(); i++) {
            tb.get(i).set("tablerow",ls.getTables());
        }
        renderAppJson(tb);

    }

    @Clear
    public void setTables(){
        ls.addData();
    }

}
