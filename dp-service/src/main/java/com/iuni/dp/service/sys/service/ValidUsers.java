package com.iuni.dp.service.sys.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iuni.dp.persist.common.constants.SysCons;
import com.iuni.dp.persist.sys.model.User;

/**
 * 启动时加载被冻结的用户到一个Map中
 * 被冻结的用户立即加入Map中
 * 解冻的用户立即从Map中除去
 * 拦截器除检查用户是否登录外，还检查用户是否在被冻结用户的Map中
 * @author Liangyongx
 */
public class ValidUsers {
	
	// 保存未被冻结的用户
	public static final Map<Long, User> inValidaUsersMap = new ConcurrentHashMap<Long, User>();
	
	private static final int STATUS= 0;// 冻结
	
	private final static Logger logger = LoggerFactory.getLogger(ValidUsers.class);
 
	public ValidUsers() {
		super();		 
	}
	
	public ValidUsers(UserService userService) {
		logger.info("---------冻结用户加载----------");
		User user = new User();
		user.setStart(0L);
		user.setEnd(Integer.MAX_VALUE);
		user.setStatus(STATUS);
		List<User> list = userService.getAllUserPage(user);
		
		if(CollectionUtils.isNotEmpty(list)){
			for(User usr : list){
				if(usr != null){
					inValidaUsersMap.put(usr.getUser_id(), usr);
				}
			}
		}
		logger.info("----------冻结用户加载 end----------");
	}
	
	public static void addOrRemoveFrozenUser(User usr){
		int status = usr.getStatus();
		if(status == SysCons.USER_STATUS_FROZEN)// 冻结
			ValidUsers.inValidaUsersMap.put(usr.getUser_id(), usr);
		else
			ValidUsers.inValidaUsersMap.remove(usr.getUser_id());
	}
	
	public static void addOrRemoveFrozenUser(String[] ids , User usr){
		int status = usr.getStatus();
		if(ids != null){
			if(status == SysCons.USER_STATUS_FROZEN){// 冻结
				for(int i = 0; i < ids.length; i++){
					ValidUsers.inValidaUsersMap.put(Long.valueOf(ids[i]), usr);
				}
			}else{
				for(int i = 0; i < ids.length; i++){
					ValidUsers.inValidaUsersMap.remove(Long.valueOf(ids[i]));
				}	
			}
		}
	}

}
