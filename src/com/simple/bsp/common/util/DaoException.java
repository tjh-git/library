/**
 * 
 */
package com.simple.bsp.common.util;

import org.springframework.core.NestedRuntimeException;

/**
 * @author simple
 * @date 2013-07-10
 */
public class DaoException extends NestedRuntimeException {

	private static final long serialVersionUID = 7963937420447431847L;

	public DaoException(String msg) {
		super(msg);
	}
	
	public DaoException(String msg,Throwable obj) {
		super(msg,obj);
		System.out.println("捕获运行异常-["+msg+"]");
	}
}
