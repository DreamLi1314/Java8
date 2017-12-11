package com.inetsoft.datetime;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import org.junit.Test;

/**
 * @Description: 时间校正器
 * @Warning:
 * @Author DreamLi
 * @Package Java8 -- com.inetsoft.datetime.DateTimeTest03
 * @Date: 2017年12月11日 下午8:59:40
 * @Version: 1.0.0
 */
public class DateTimeTest03 {

	/**
	 * @Description: 获取当前月的第一天
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年12月11日 下午9:01:48
	 */
	@Test
	public void test01() {
		TemporalAdjuster adjuster = TemporalAdjusters.firstDayOfMonth();
		LocalDateTime dateTime = LocalDateTime.now();
		Temporal adjustInto = adjuster.adjustInto(dateTime);
		System.out.println(adjustInto);
	}

	/**
	 * @Description: 获取下一个周日 
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年12月11日 下午9:03:03
	 */
	@Test
	public void test02() {
		LocalDateTime nextSunday = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
		System.out.println(nextSunday);
	}
	
	/**
	 * @Description: 获取下一个工作日 
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年12月11日 下午9:07:06
	 */
	@Test
	public void test03() {
		LocalDateTime workDay = LocalDateTime.now().with((dt) -> {
			LocalDateTime ldt = (LocalDateTime) dt;
			if(ldt.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
				return ldt.plusDays(3);
			} else if (ldt.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
				return ldt.plusDays(2);
			} else {
				return ldt.plusDays(1);
			}
		});
	
		System.out.println(workDay);
	}

}
