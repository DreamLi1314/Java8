package com.inetsoft.datetime;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Description: 新老API操作的对比
 * @Warning: @Test 和在 main 函数运行效果不同: @Test 运行 test01() 不会报错
 * @Author DreamLi
 * @Package Java8 -- com.inetsoft.datetime.DateTimeTest
 * @Date: 2017年12月11日 上午10:47:39
 * @Version: 1.0.0
 */
public class DateTimeTest01 {
	
	public static void main(String[] args) throws Exception {
//		test01(); // Error
//		test02(); // Right
		test03(); // Java 8
	}

	/**
	 * @Description: Java8 新的时间日期 API 
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年12月11日 上午11:18:33
	 */
	public static void test03() throws Exception {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");//DateTimeFormatter.ISO_DATE_TIME
	
		ExecutorService pool = Executors.newFixedThreadPool(10);
	
		Callable<LocalDateTime> task = new Callable<LocalDateTime>() {
			@Override
			public LocalDateTime call() throws Exception {
				return LocalDateTime.parse("20171211 11:25:59", dtf);
			}
		};
	
		List<Future<LocalDateTime>> dates = new ArrayList<>();
	
		for (int i = 0; i < 10; i++) {
			Future<LocalDateTime> date = pool.submit(task);
			dates.add(date);
		}
	
		for (Future<LocalDateTime> future : dates) {
			System.out.println(future.get());
		}
		
		pool.shutdown();
	}

	/**
	 * @Description: 解决 test01 存在的多线程问题 
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年12月11日 上午11:17:34
	 * @throws Exception
	 */
	public static void test02() throws Exception {
		ExecutorService pool = Executors.newFixedThreadPool(10);
	
		Callable<Date> task = new Callable<Date>() {
			@Override
			public Date call() throws Exception {
				return DateTimeThreadLocal.convert("20171211");
			}
		};
	
		List<Future<Date>> dates = new ArrayList<>();
	
		for (int i = 0; i < 10; i++) {
			Future<Date> date = pool.submit(task);
			dates.add(date);
		}
	
		for (Future<Date> future : dates) {
			System.out.println(future.get());
		}
		
		pool.shutdown();
	}
	
	/**
	 * @Description: 传统时间日期 API 都存在线程安全问题 
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年12月11日 上午11:17:57
	 * @throws Exception
	 */
	public static void test01() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
		ExecutorService pool = Executors.newFixedThreadPool(10);
	
		Callable<Date> task = new Callable<Date>() {
			@Override
			public Date call() throws Exception {
				return sdf.parse("20171211");
			}
		};
	
		List<Future<Date>> dates = new ArrayList<>();
	
		for (int i = 0; i < 10; i++) {
			Future<Date> date = pool.submit(task);
			dates.add(date);
		}
	
		for (Future<Date> future : dates) {
			System.out.println(future.get());
		}
		
		pool.shutdown();// 线程池用完需要 shutdown
	}

}
