package com.simple.bsp.security.web.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.simple.bsp.common.web.controller.BaseController;
import com.simple.bsp.security.po.PubUsers;
import com.simple.bsp.user.service.UserService;

/**
 * 登录校验由Spring Security完成，此处仅作页面转向
 * @author simple
 *
 */
@Controller
public class LoginController extends BaseController{
	
	private static Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;

	/**
	 * 登录成功后进入系统欢迎页面
	 * @param userName
	 * @param passWord
	 * @return
	 */
	@RequestMapping(value="/welcome",method=RequestMethod.GET)
	public String login(HttpSession session, HttpServletRequest request){
		//获取当前登录用户
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers)principal;
			
			if(user.getEnable().equals("0")){
				//用户锁定
				return "bsp/security/logfail";
			}else{
				//用户登录成功后将登录错误次数清零
				userService.updateEnable(user.getUserAccount(), "0", "1");
			}
			
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("userAccount", user.getUserAccount());
			session.setAttribute("userName", user.getUserName());
			session.setAttribute("userOrg", user.getUserOrg());
		}
		return "bsp/layout/layout";
	}
	
	/**
	 * 登录成功前将用户账号保存在session中（用户登录失败时记录用户账号）
	 * @return
	 */
	@RequestMapping(value="/setUserName")
	public String setUserName2Session(@RequestParam("userName") String userName, HttpSession session){
		//用户账号存在时进行session缓存
		if(userService.getUserCountByAcc(userName) > 0){
			logger.info("["+userName+"]存入session[userName]!");
			session.setAttribute("userName", userName);
		}else{
			logger.info("["+userName+"]在系统中不存在,session[userName]置为空!");
			session.setAttribute("userName", "");
		}
		return null;
	}
	
	/**
	 * 进入登录失败页面
	 * @return
	 */
	@RequestMapping(value="/logfail")
	public String logFail(HttpSession session){
		String userName = (String)session.getAttribute("userName");
		logger.info("当前登录失败的用户账号为:["+session.getAttribute("userName")+"]");
		
		if(null != userName && !userName.equals("")){
			//获取登录失败次数
			Map<String, Object> map = userService.getErrTimes(userName);
			if(null != map){
				int errTimes = Integer.parseInt((map.get("err_times") == null)?"0":map.get("err_times").toString());
				String isEnable = "1";
				if(errTimes < 10){	//失败次数少于10次时，每次累加
					errTimes ++;
					userService.updateEnable(userName, errTimes+"", isEnable);
					logger.info("["+userName+"]保存登录次数:["+errTimes+"],用户状态:["+isEnable+"]成功!");
				}else{				//失败次数等于10次时，状态enable置为不可用'0'，锁定
					isEnable = "0";
					userService.updateEnable(userName, errTimes+"", isEnable);
					logger.info("["+userName+"]锁定!");
					return "bsp/security/lock";
				}
			}
		}
		return "bsp/security/logfail";
	}
	
	/**
	 * 进入拒绝访问页面
	 * @return
	 */
	@RequestMapping(value="/accessDenied")
	public String accessDenied(){
		return "bsp/security/403";
	}
	
	/**
	 * 进入会话超时页面
	 * @return
	 */
	@RequestMapping(value="/timeout")
	public String sessionTimeOut(){
		return "bsp/security/timeout";
	}
	
	/**
	 * 退出系统
	 * @return
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/logout")
	public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		//清除session
        Enumeration<String> em = request.getSession().getAttributeNames();
        while(em.hasMoreElements()){
            request.getSession().removeAttribute(em.nextElement().toString());
        }
        request.getSession().invalidate();
        
        response.sendRedirect("index.jsp");
	}
	@RequestMapping(value = "404", method = RequestMethod.GET)
	public String redirect404(HttpServletRequest request){
		return "bsp/security/404";
	} 
	@RequestMapping(value = "500", method = RequestMethod.GET)
	public String redirect500(HttpServletRequest request){
		return "bsp/security/500";
	}	
}
