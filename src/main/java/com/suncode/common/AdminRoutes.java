package com.suncode.common;

import com.suncode.common.interceptors.LoginInterceptor;
import com.jfinal.aop.Before;
import com.jfinal.config.Routes;

public class AdminRoutes extends Routes {

	@Override
	@Before(LoginInterceptor.class)
	public void config() {
		//System
//		add("/", IndexControllers.class);	// 第三个参数为该Controller的视图存放路径
//		add("/sys_user",UserController.class,"/pages/system"); //系统登录用户
//		add("/sys_log",SysLogController.class,"/pages/system");//系统管理员操作日志		// 第三个参数省略时默认与第一个参数值相同，在此即为 "/blog"
//		add("/sys_auth", AuthController.class, "/pages/system");//系统管理员角色分配
		//test
//		add("/content", ContentController.class, "/pages/content");
	}

}
