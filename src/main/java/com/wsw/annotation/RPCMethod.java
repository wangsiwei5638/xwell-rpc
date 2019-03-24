package com.wsw.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**   
 * @ClassName:  RPCMethod   
 * @Description:
 * 
 * 					表示该方法是一个RPC方法
 * 
 * @author: wsw
 * @date:   2019年3月23日
 * @Copyright: http://www.iwangsiwei.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RPCMethod {

	public boolean isPrintLog() default false;
	
	
}
