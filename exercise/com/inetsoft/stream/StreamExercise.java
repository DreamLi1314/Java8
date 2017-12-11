package com.inetsoft.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;

import com.inetsoft.lambda.User;

public class StreamExercise {

	/*
	 * 1. 给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？ ，给定【1，2，3，4，5】， 应该返回【1，4，9，16，25】。
	 */
	@Test
	public void test01() {
		Integer[] nums = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8 };
		List<Integer> collect = Arrays.stream(nums)
			  .map((num) -> num * num)
			  .collect(Collectors.toList());
		
		System.out.println(collect);
	}

	List<User> users = Arrays.asList(
			new User("11", 11, "aaaa", 999.99),
			new User("06", 6, "cccc", 1119.99),
			new User("19", 19, "bbbb", 89.99),
			new User("01", 1, "dddd", 99.99),
			new User("01", 1, "dddd", 99.99),
			new User("30", 30, "ffff", 889.99)
	);
	
	
	/*
	 * 2. 怎样用 map 和 reduce 方法数一数流中有多少个User呢？
	 */
	@Test
	public void test02() {

		Optional<Integer> sum = users.stream()
			 .map((user) -> 1)
			 .reduce(Integer::sum);
		
		System.out.println(sum.get());
	}

}
