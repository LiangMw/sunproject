package com.suncode.common.interceptors;

import com.suncode.common.base.BaseController;
import com.suncode.common.base.SysStatus;
import com.suncode.common.model.bean.Token;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.kit.HashKit;
import com.jfinal.kit.StrKit;

public class AutherIntercepter implements Interceptor{

	@Override
	public void intercept(Invocation inv) {
		// TODO Auto-generated method stub
		String guid = inv.getController().getPara("guid");
		String sign = inv.getController().getPara("sign");
		String token = getToken(guid);
		System.out.println( token);
		System.out.println( inv.getControllerKey() + "/" + inv.getMethodName() + " ,  " + inv);
		System.out.println( inv.getActionKey());
		String key = inv.getControllerKey() + "/" + inv.getMethodName();
		if (token.equals(SysStatus.CODETOKENERROR)) {
			((BaseController) inv.getController()).renderAppErrorAndCode("Token错误，请重新登录！", SysStatus.CODETOKENERROR);
			return;
		}
		System.out.println( inv.getActionKey());
		if(!checkToken(sign,inv.getActionKey(),token)){
			((BaseController) inv.getController()).renderAppErrorAndCode("认证信息出错，请重新登录！", SysStatus.CODETOKENERROR);
			return;
		}
		inv.invoke();
	}

	/**
	 * 根据请求中加密后的sign信息验证请求中的token是否合法 sign为md5(url+token)的签名。eg:md5(/user/info?token=qfSMacpcaTJZPrkk)
	 *
	 * @param cKey
	 * @return
	 */
	public static boolean checkToken(String sign, String cKey,String token) {
		//String uid = c.getPara("uid");
		if (StrKit.isBlank(sign)) {
			return false;
		}
		if (token != null) {
			String newsign;
			newsign = cKey + "?token=" + token;
			System.out.println("----"+HashKit.md5(newsign));
			if (sign.equals(HashKit.md5(newsign))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 根据guid查询token
	 * @param guid
	 * @return
	 */
	private static String getToken(String guid){
		Token t = Token.dao.findFirst("select t.id, t.token from s_token t left join s_user u on t.guid = u.guid where u.guid=?",guid);
		if(t !=null){
			return StrKit.isBlank(t.getToken())?SysStatus.CODETOKENERROR:t.getToken();
		}else{
			return SysStatus.CODETOKENERROR;
		}
	}
}
