package com.inetsoft.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import org.junit.Test;


public class Test1 {
	List<User> list = Arrays.asList(
			new User("11", 11, "aaaa", 999.99),
			new User("06", 6, "cccc", 1119.99),
			new User("19", 19, "bbbb", 89.99),
			new User("01", 1, "dddd", 99.99),
			new User("30", 30, "ffff", 889.99)
	);
	
	@Test
	public void test1() {
		TreeSet<Integer> tree = new TreeSet<Integer>((x, y) -> Integer.compare(x, y));
		tree.add(3);
		tree.add(1);
		tree.add(4);
		tree.add(2);
		
		tree.forEach(System.out::println);
	}
	
	//找出集合中年龄大于15岁的user
	@Test
	public void test2() {
		list.stream()
			.filter(u -> u.getAge() > 15)
			.limit(1)
			.forEach(System.out::println);
	}
	
	//打印所有用户的名字
	@Test
	public void test3() {
		list.stream()
			.map(User::getName)
			.forEach(System.out::println);
	}
}
