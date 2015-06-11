package com.iuni.dp.admin.sys.facade.impl;

import java.net.MalformedURLException;

import com.iuni.dp.admin.common.utils.HessianServiceFactory;
import com.iuni.dp.admin.sys.facade.SystemFacade;
import com.iuni.dp.service.common.bean.Result;

public class SystemFacadeImplTest {

	public static void main(String[] args) throws MalformedURLException {
		authenticate();
		//getMenuList();
		//getUserInfo();
		//getSystemInfo();
	}
	
	public static void authenticate() {
		Result rs = getSystemFacade().authenticate(loginName, password);
        if(rs.getState().equals("1")){
        	System.out.println("call authenticate() success :"+rs.getData().toString());
        }else{
        	System.out.println("call authenticate() fail :"+rs.getErrorMessage());
        }
	}
	
	public static void getMenuList() {
		Result rs = getSystemFacade().getMenuList(sysIds, optId);
        if(rs.getState().equals("1")){
        	System.out.println("call getMenuList() success :"+rs.getData().toString());
        }else{
        	System.out.println("call getMenuList() fail :"+rs.getErrorMessage());
        }
	}
	
	public static void getUserInfo() {
		Result rs = getSystemFacade().getUserInfo(loginName,optId);
        if(rs.getState().equals("1")){
        	System.out.println("call getUserInfo() success :"+rs.getData().toString());
        }else{
        	System.out.println("call getUserInfo() fail :"+rs.getErrorMessage());
        }
	}
	
	public static void getSystemInfo() {
		Result rs = getSystemFacade().getSystemInfo(sysName,sysId);
        if(rs.getState().equals("1")){
        	System.out.println("call getSystemInfo() success :"+rs.getData().toString());
        }else{
        	System.out.println("call getSystemInfo() fail:"+rs.getErrorMessage());
        }
	}
    
    private static SystemFacade getSystemFacade(){
    	SystemFacade systemFacade = null;
		try {
			systemFacade = (SystemFacade) HessianServiceFactory.getSystemFacade(remoteUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return systemFacade;
	}
    
    //static final String localUrl = "http://localhost:8080/remote/systemFacade";
    static final String remoteUrl = "http://rm.cm.com:8080/remote/systemFacade";
    private static final String loginName = "admin";
    private static final String password = "admin";
    private static final String sysName = "金立业务运营平台";
    private static final String sysId = "1";
    private static final String[] sysIds = {"4","5"};
    private static final String optId = "99";
    
}
