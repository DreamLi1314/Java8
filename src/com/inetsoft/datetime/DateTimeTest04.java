package com.inetsoft.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import org.junit.Test;
/**
 * @Description: 时间日期的解析、格式化 与 时区操作
 * @Warning: 
 * @Author DreamLi
 * @Package Java8  --  com.inetsoft.datetime.DateTimeTest04
 * @Date: 2017年12月11日 下午9:12:58
 * @Version: 1.0.0
 */
public class DateTimeTest04 {

	/**
	 * @Description: 将时间日期格式化为日期类型 
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年12月11日 下午9:20:12
	 */
	@Test
	public void test01() {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
		String str1 = formatter.format(LocalDateTime.now());
		System.out.println(str1);
		
		//将字符串解析为日期类型
		LocalDate parse = LocalDate.parse(str1, formatter);
		System.out.println(parse);
	}
	
	/**
	 * @Description:  
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年12月11日 下午9:23:51
	 */
	@Test
	public void test02() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String format = LocalDateTime.now().format(formatter);
		System.out.println(format);
		
		LocalDate parse = LocalDate.parse(format, formatter);
		System.out.println(parse);
	}
	
	/**
	 * @Description: 获取所有支持的时区 
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年12月11日 下午9:30:09
	 */
	@Test
	public void test03() {
		Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
		for (String zoned : availableZoneIds) {
			System.out.println(zoned);
		}
	}
	
	/**
	 * @Description:  
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年12月11日 下午9:33:13
	 */
	@Test
	public void test04() {
		ZonedDateTime zonedDateTime = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Asia/Srednekolymsk"));
		System.out.println(zonedDateTime);
	}
	
	
}
