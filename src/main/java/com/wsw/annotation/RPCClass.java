package com.wsw.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**   
 * @ClassName:  RPCClass   
 * @Description:
 * 
 * 					该注解作用在类上，表示该类是一个RPC类
 * 
 * @author: wsw
 * @date:   2019年3月23日
 * @Copyright: http://www.iwangsiwei.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RPCClass {

	
	
}
