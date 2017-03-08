/**
 * 
 */
package com.simple.bsp.common.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 自定义拦截器
 * @author simple
 * @date 2013-07-10
 */
public class MyInterceptor extends HandlerInterceptorAdapter {

	/**
	 * Controller之前执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		return super.preHandle(request, response, handler);
	}
}
