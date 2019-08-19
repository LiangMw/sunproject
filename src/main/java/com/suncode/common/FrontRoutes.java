package com.suncode.common;

import com.suncode.common.functionapi.login.LoginController;
import com.suncode.common.interceptors.AutherIntercepter;
import com.jfinal.config.Routes;


public class FrontRoutes extends Routes {

//	@Before(AutherIntercepter.class)
	@Override
	public void config() {
		addInterceptor(new AutherIntercepter());
		add("/login", LoginController.class);
//		add("/music",MusicController.class);
//		add("/fc",FunctionController.class);
//		add("/test",TestController.class);

	}

}
