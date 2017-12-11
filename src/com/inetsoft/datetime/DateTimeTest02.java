package com.inetsoft.datetime;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class DateTimeTest02 {
	
	@Test
	public void test01() {
		LocalDate date = LocalDate.now();
		System.out.println(date);
		
		LocalTime time = LocalTime.now();
		System.out.println(time);
		
		LocalDateTime dateTime = LocalDateTime.now();
		System.out.println(dateTime);
		
		System.out.println(dateTime.getYear());
		System.out.println(dateTime.getMonthValue());
		System.out.println(dateTime.getDayOfMonth());
		System.out.println(dateTime.getHour());
		System.out.println(dateTime.getMinute());
		System.out.println(dateTime.getSecond());
	}
	
	/**
	 * @Description: 计算一段程序运行所消耗的时间 
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年12月11日 下午4:58:22
	 */
	@Test
	public void test02() throws Exception {
		Instant start = Instant.now();
		
		TimeUnit.SECONDS.sleep(2);
		
		Instant end = Instant.now();
		
		System.out.println(end.toEpochMilli() - start.toEpochMilli());
		
		System.out.println("---------------------------------------------------");
		
		Duration duration = Duration.between(start, end);
		System.out.println(duration.getSeconds());
		System.out.println(duration.toMillis());
	}
	
	@Test
	public void test03() {
		Instant instant = Instant.now();
		System.out.println(instant);
		System.out.println(instant.plusMillis(1000));// +1s
		System.out.println(instant.minusMillis(1000));// -1s
	}
	
	/**
	 * @Description: Duration : 两个时间之间的间隔 
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年12月11日 下午5:39:05
	 */
	@Test
	public void test04() throws Exception {
		Instant ins1 = Instant.now();
		
		TimeUnit.SECONDS.sleep(2);
		
		Instant ins2 = Instant.now();
	
		System.out.println(Duration.between(ins1, ins2).toMillis());
		
		System.out.println("-----------------------------------------------------");
		
		LocalTime lt1 = LocalTime.now();
		
		TimeUnit.SECONDS.sleep(2);
		
		LocalTime lt2 = LocalTime.now();
		
		System.out.println(Duration.between(lt1, lt2).toMillis());
	}
	
	/**
	 * @Description: Period : 两个日期之间的间隔
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年12月11日 下午5:49:39
	 */
	@Test
	public void test05() throws Exception {
		LocalDate ld1 = LocalDate.of(2016, 10, 25);

		TimeUnit.SECONDS.sleep(2);

		LocalDate ld2 = LocalDate.now();

		Period period = Period.between(ld1, ld2);

		System.out.println(period);
		System.out.println(period.getYears());
		System.out.println(period.getMonths());
		System.out.println(period.getDays());
	}
	
}
