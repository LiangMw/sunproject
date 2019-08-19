package com.suncode.common.interceptors;

import com.suncode.common.model.bean.User;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class LoginInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		Controller controller=inv.getController();
		User loginUser=controller.getSessionAttr("loginUser");
		if(loginUser!=null){
			inv.invoke();
		}else{
//			controller.renderJson(DwzJson.timeout("会话超时！请重新登录！", "", ""));
		}
		
	}
	
}
