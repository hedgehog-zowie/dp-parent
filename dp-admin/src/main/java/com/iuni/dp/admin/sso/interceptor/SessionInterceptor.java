package com.iuni.dp.admin.sso.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.struts2.ServletActionContext;

import com.iuni.dp.admin.sso.constants.SsoConstant;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;



/**
 * 判断session是否超时的拦截器
 * 超时跳到登录页面
 * @author kevin
 */
public class SessionInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 7579862236766378267L;

	@Override  
    public String intercept(ActionInvocation actionInvocation) throws Exception { 
/*		HttpServletRequest request = ServletActionContext.getRequest();
    	if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With")) || request.getParameter("ajax") != null) {
    		
    		if(!SecurityUtils.getSubject().isAuthenticated()){
    			String msg = "{\"statusCode\":\"301\", \"message\":\"Session Timeout! Please re-sign in!\"}";
    			ServletActionContext.getResponse().getWriter().write(msg);
    			return null;
    		}
    	}*/
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		if(!SecurityUtils.getSubject().isAuthenticated()){
			//普通超时处理
			String callbackUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + SsoConstant.UUC_AUTHC_CALLBACK_URI;
			String ssoUrl = SsoConstant.UUC_URL+SsoConstant.UUC_AUTHC_TICKET_URI + "service=" + callbackUrl;
			//调用IUC SSO接口，发送重定向 ，类似： ssoUrl=http://passport.cm.com/iuc/sso?service=http://18.8.0.28:8080/wms/authcCallback
			response.sendRedirect(response.encodeRedirectURL(ssoUrl));
		}
       return actionInvocation.invoke();  
    }
}