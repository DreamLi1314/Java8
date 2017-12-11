package com.inetsoft.datetime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @Description: 用来解决传统的时间日期类存在的线程安全问题 
 * @Warning: 
 * @Author DreamLi
 * @Package Java8  --  com.inetsoft.datetime.DateTimeThreadLocal
 * @Date: 2017年12月11日 上午11:12:39
 * @Version: 1.0.0
 */
public class DateTimeThreadLocal {

	private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {

		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyyMMdd");
		}
		
	};
	
	public static Date convert(String source) throws Exception {
		return df.get().parse(source);
	}
	
}
