package com.suncode.common.functionapi.login;


import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.suncode.common.model.bean.Table;
import com.suncode.common.model.bean.User;

import java.util.List;

/**
 * Created by 梁明伟 on 2019/8/16.
 * Copyright © 2018年 CETC. All rights reserved.
 */

public class LoginService {
    private static User u = new User();
    private static Table tb = new Table();

    public User getUserInfo(String username){
        return u.findFirst("select * from s_user u where u.username = ?",username);
    }

    public List<Record> getTables(){

        return Db.find("select t.id,t.title,t.value from t_table t where 1=1");

    }

    public void addData(){
        for (int i = 0;i<10;i++){
            Table tb = new Table();
            tb.setTitle("标题"+(i+1));
            tb.setValue((1+Math.random())+"");
            tb.save();
        }
    }

}
