package com.inetsoft.lambda;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Test;


public class Test2 {
	
	
	@Test
	public void test1() {
		Runnable run1 = new Runnable() {
			@Override
			public void run() {
				System.out.println("Hello old action");
			}
		};
		run1.run();
		
		Runnable run2 = () -> System.out.println("Hello lambda action");
		run2.run();
	}
	
	private List<User> list = Arrays.asList(
			new User("11", 11, "aaaa", 999.99),
			new User("06", 6, "cccc", 1119.99),
			new User("19", 11, "bbbb", 89.99),
			new User("01", 1, "dddd", 99.99),
			new User("30", 30, "ffff", 889.99)
			);
	
	//调用 Collections.sort 方法,先按年龄排序后按姓名排序  int compare(T o1, T o2);
	@Test
	public void test2() {
		Collections.sort(list, (u1, u2) -> {
			if(u1.getAge() != u2.getAge()) {
				return Integer.compare(u1.getAge(), u2.getAge());
			}
			
			return u1.getName().compareTo(u2.getName());
		});
		
		for (User user : list) {
			System.out.println(user);
		}
	}
	
	//通过函数式接口实现将字符串转化为大写, 截取字符串等处理操作
	@Test
	public void test3() {
		String s = "aaUbb";
		String ns = this.convert(s, (str) -> str.toUpperCase());
		System.out.println(ns);
	}
	
	private String convert(String src, FuncInterface1 rule) {
		return rule.getValue(src);
	}
	
	@Test
	public void test4() {
		String s = "Hello World!";//截取6到11
		String convert = this.convert(s, (str) -> str.substring(6, 11));
		System.out.println(convert);
	}
	
	private long operation(long x, long y, FuncInterface2<Long, Long> rule) {
		return rule.getValue(x, y);
	}
	
	@Test
	public void test5() {
		long sum = operation(10L, 20L, (x, y) -> x + y);
		System.out.println(sum);
		long product = operation(10L, 20L, (x, y) -> x * y);
		System.out.println(product);
	}
	
	
}