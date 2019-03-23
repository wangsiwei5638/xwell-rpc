package com.wsw.protocol;

import com.wsw.bean.URL;

/**   
 * @ClassName:  Protocol   
 * @Description:
 * 					基础协议接口
 * @author: wsw
 * @date:   2019年3月23日
 * @Copyright: http://www.iwangsiwei.com
 */
public interface Protocol {

	/**
	 * 	启动协议
	 */
	void start(URL url);
}
