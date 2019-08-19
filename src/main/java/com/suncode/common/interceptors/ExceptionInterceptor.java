package com.suncode.common.interceptors;

import com.suncode.common.base.BaseController;
import com.suncode.common.exception.MyException;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.kit.LogKit;

import java.util.Map;
public class ExceptionInterceptor  implements Interceptor{

	
	@Override
	public void intercept(Invocation ai) {
		try {
			ai.invoke();
		} catch (MyException e) {
			// e.getMessage()以[HOLDINFO]开头的为自定义错误,单独处理
			if (e.getMessage() != null && e.getMessage().startsWith("[HOLDINFO]")) {
				((BaseController) ai.getController()).renderAppError(e.getMessage().substring(10, e.getMessage().length()));
			} else {
				((BaseController) ai.getController()).renderAppError(e.forCustomer);

				String parasstr = ConvertParameterMapToString(ai);
				LogKit.error(e.forDev + "，" + ai.getControllerKey() + "/" + ai.getMethodName() + " ,  " + parasstr + " ,  " + e.getMessage());
				LogKit.error(e.getMessage(), e);
			}
		}catch (Exception e) {
				// ...
				((BaseController) ai.getController()).renderAppError("拦截到错误:" + e.toString());
				//
			}
	}
	
	public static String ConvertParameterMapToString(Invocation ai) {
		Map<String, String[]> parasmap = ai.getController().getRequest().getParameterMap();
		String parasstr = "?";
		try {

			for (Object obj : parasmap.keySet()) {
				// Object valueobj = parasmap.get(obj);
				// String[] valuestrlist = (String[]) valueobj;
				String[] valuestrlist = (String[]) parasmap.get(obj);
				String valuestr = "";
				for (String valuetmp : valuestrlist) {
					valuestr += valuetmp + ",";
				}
				if (valuestr.length() > 0)
					valuestr = valuestr.substring(0, valuestr.length() - 1);
				parasstr += obj + "=";
				parasstr += valuestr + "&";
			}
			if (parasstr.length() > 0)
				parasstr = parasstr.substring(0, parasstr.length() - 1);
		} catch (Exception e) {
			LogKit.error(e.getMessage(), e);
		}
		return parasstr;

	}

}
