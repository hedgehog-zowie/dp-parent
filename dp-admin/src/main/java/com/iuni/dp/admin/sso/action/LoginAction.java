package com.iuni.dp.admin.sso.action;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.iuni.dp.admin.common.utils.ActionUtils;
import com.iuni.dp.admin.common.utils.ValidateCode;
import com.iuni.dp.admin.sso.constants.SsoConstant;
import com.iuni.dp.admin.sso.dto.Menu;
import com.iuni.dp.admin.sso.dto.ShiroUser;
import com.iuni.dp.admin.sso.service.AccountService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @ClassName: LoginAction
 * @Description: 登录
 * @author Kevin
 * @date 2013-4-20 下午03:50:21
 * @version dp-task-1.0.0
 */
@Controller("loginAction")
@Scope("prototype")
public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = 2065002428534566458L;
	private String loginName;
	private String password;
	private String submitCode;
	private StringBuffer menuHtml = new StringBuffer();
	private String ssoLoginUrl;// SSO登录地址
	private AccountService accountService;

	/**
	 * 本地登录(已取消)
	 * @return
	 */
	public String doLogin() {
		if (accountService.isAuthenticated()) {
			return SUCCESS;
		}
		HttpSession session = ServletActionContext.getRequest().getSession();
		String validateCode = (String) session.getAttribute("validateCode");
		session.removeAttribute("validateCode");
		if (StringUtils.isBlank(submitCode) || !submitCode.equalsIgnoreCase(validateCode)) {
			addActionMessage("验证码不正确.");
			return "fail";
		}
		try {
			accountService.authenticate(loginName, password);
			return SUCCESS;
		} catch (Exception e) {
			addActionMessage(e.getMessage());
		}
		return "fail";
	}

	/**
	 * 生成验证码
	 * 
	 * @return byte[]
	 * @throws IOException
	 */
	public void validateCode() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		// 设置浏览器不缓存本页
		response.setHeader("Cache-Control", "no-cache");
		// 生成验证码，写入用户session
		String verifyCode = ValidateCode.generateTextCode(ValidateCode.TYPE_NUM_ONLY, 4, null);
		request.getSession().setAttribute("validateCode", verifyCode);
		// 输出验证码给客户端
		response.setContentType("image/jpeg");
		BufferedImage bim = ValidateCode.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(bim, "JPEG", out);
		// 解决IllegalStateException 异常
		out.flush();
		out.close();
		response.getWriter().close();
	}

	/**
	 * 初始化首页数据
	 */
	public String index() {
		//初始化系统菜单
		//ShiroUser user = accountService.getCurrentUser();
		//generateTree(user.getMenuList());
		// generateTree(accountService.getPermissions(null));
		
		//初始化SSO登录地址
		String callbackUrl = "http://" + ActionUtils.getRequest().getServerName() + ":"
				+ ActionUtils.getRequest().getServerPort() + ActionUtils.getRequest().getContextPath()
				+ SsoConstant.UUC_AUTHC_CALLBACK_URI;
		ssoLoginUrl = SsoConstant.UUC_URL + SsoConstant.UUC_AUTHC_TICKET_URI + "service=" + callbackUrl;
		return SUCCESS;
	}

	/**
	 * 退出
	 */
	public String logout() throws IOException {
		// 退出本地会话
		ActionUtils.logout();
		
		// 重定向到SSO退出接口
		String callbackUrl = "http://" + ActionUtils.getRequest().getServerName() + ":"
				+ ActionUtils.getRequest().getServerPort() + ActionUtils.getRequest().getContextPath()
				+ SsoConstant.UUC_AUTHC_CALLBACK_URI;
		String ssoUrl = SsoConstant.UUC_URL + SsoConstant.UUC_AUTHC_LOGOUT_URI + "s=y" + "&service=" + callbackUrl;
		ActionUtils.getResponse().sendRedirect(ActionUtils.getResponse().encodeRedirectURL(ssoUrl));
		return null;
	}

	/**
	 * 获取菜单列表
	 */
	public String getMenus() {
		//初始化系统菜单
		ShiroUser user = accountService.getCurrentUser();
		generateTree(user.getMenuList());
		return SUCCESS;
	}
	/**
	 * 递归生成菜单树
	 */
	private void generateTree(List<Menu> menuList) {
		for (Menu menu : menuList) {
			if (CollectionUtils.isNotEmpty(menu.getChild())) {
				menuHtml.append("<h3 class=\"trigger\">" + menu.getName()+ "</h3>");
				menuHtml.append("<ul class=\"list\">");
				//递归
				generateTree(menu.getChild());
				menuHtml.append("</ul>");
			} else {
				menuHtml.append("<li><a href=\"" + ActionUtils.getRequest().getContextPath()+ menu.getPath()+ "\" target=\"mainFrame\">"+ menu.getName()+ "<span class=\"nav_baright\"></span></a></li>");
			}

		}
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSubmitCode() {
		return submitCode;
	}

	public void setSubmitCode(String submitCode) {
		this.submitCode = submitCode;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public StringBuffer getMenuHtml() {
		return menuHtml;
	}

	public String getSsoLoginUrl() {
		return ssoLoginUrl;
	}

	public void setSsoLoginUrl(String ssoLoginUrl) {
		this.ssoLoginUrl = ssoLoginUrl;
	}

	@Autowired
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

}
