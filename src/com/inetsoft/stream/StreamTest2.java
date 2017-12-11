package com.inetsoft.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Test;


/**
 * @Description: 终止操作
 * @Warning:
 * @Author DreamLi
 * @Package Java8 -- com.inetsoft.stream.StreamTest2
 * @Date: 2017年11月19日 下午4:42:51
 * @Version: 1.0.0
 */
public class StreamTest2 {

	List<User> users = Arrays.asList(new User("11", 11, "aaaa", 999.99), 
									 new User("06", 6, "cccc", 1119.99),
									 new User("19", 19, "bbbb", 89.99), 
									 new User("01", 1, "dddd", 99.99), 
									 new User("01", 1, "dddd", 99.99),
									 new User("30", 30, "ffff", 889.99));

	/**
	 * @Description: match 匹配 
	 * 				 allMatch——检查是否匹配所有元素 
	 *  			 anyMatch——检查是否至少匹配一个元素
	 *               noneMatch——检查是否没有匹配的元素
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年11月19日 下午4:43:11
	 */
	@Test
	public void test1() {
		boolean allMatch = users.stream()
		 	 .filter((u) -> u.getAge() > 10)
		 	 .allMatch((u) -> u.getAge() > 10);
		System.out.println(allMatch);
		
		boolean anyMatch = users.stream()
			 .filter((u) -> u.getAge() > 10)
			 .anyMatch((u) -> u.getAge() > 20);
		System.out.println(anyMatch);
		
		
		boolean noneMatch = users.stream()
		 .filter((u) -> u.getAge() > 10)
		 .noneMatch((u) -> u.getAge() > 30);
		System.out.println(noneMatch);
	}
	
	/**
	 * @Description: find 查找
	 * 		findFirst——返回第一个元素 
	 *  	findAny——返回当前流中的任意元素
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年11月19日 下午4:51:11
	 */
	@Test
	public void test2() {
		Optional<User> findFirst = users.stream()
		 .filter((u) -> u.getAge() > 10)
		 .findFirst();
		System.out.println(findFirst.get());
		
		Optional<User> findAny = users.parallelStream()
			 .filter((u) -> u.getAge() > 10)
			 .findAny();
		System.out.println(findAny.get());
	}
	
	/**
	 * @Description: count -- 返回流中数据的总个数 
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年11月19日 下午5:13:07
	 */
	@Test
	public void test3() {
		long count = users.stream()
			 .count();
		System.out.println(count);
	}
		
	/**
	 * @Description: max/min 返回流中的最大/最小值 
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年11月19日 下午5:14:19
	 */
	@Test
	public void test4() {
		Optional<Integer> max = users.stream()
			 .map(User::getAge)
			 .max(Integer::compare);
		System.out.println(max.get());
		
		Optional<Integer> min = users.stream()
				.map(User::getAge)
				.min(Integer::compare);
		System.out.println(min.get());
	}
	
	/**
	 * @Description: Notice 
	 * @Warning: 流进行了终止操作后不能再进行操作
	 * @Author: DreamLi
	 * @Date: 2017年11月19日 下午5:16:43
	 */
	@Test
	public void test5() {
		Stream<User> stream = users.stream()
			 .filter((u) -> u.getAge() > 10d);
		
		long count = stream.count();
		System.out.println(count);
		
		Optional<User> max = stream.max((u1, u2) -> Integer.compare(u1.getAge(), u2.getAge()));
		//java.lang.IllegalStateException: stream has already been operated upon or closed line:124
		System.out.println(max.get());
	}

}
