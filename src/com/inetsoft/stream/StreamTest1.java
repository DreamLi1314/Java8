package com.inetsoft.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

import com.inetsoft.lambda.User;

public class StreamTest1 {
	
	/**
	 * @Description: Stream 的四种创建方式
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年11月19日 上午9:36:06
	 */
	@Test
	public void test1() {
		//1. Conllection
		List<String> list1 = new ArrayList<>();
		Stream<String> stream1 = list1.stream();//获取顺序流
		Stream<String> stream2 = list1.parallelStream();//获取并行流
		
		//2. Arrays
		int[] arr1 = new int[10];
		IntStream stream3 = Arrays.stream(arr1);
		
		//3. Stream.of
		Stream<Integer> stream4 = Stream.of(1,2,3,4,5,6);
		
		//4.创建无限流
		//迭代
		Stream<Integer> stream5 = Stream.iterate(0, (x) -> x + 2);
//		stream5.forEach(System.out::println);
		
		//生成
		Stream<Double> stream6 = Stream.generate(Math::random);
		stream6.forEach(System.out::println);
	}
	
	// 中间操作
	List<User> users = Arrays.asList(
			new User("11", 11, "aaaa", 999.99),
			new User("06", 6, "cccc", 1119.99),
			new User("19", 19, "bbbb", 89.99),
			new User("01", 1, "dddd", 99.99),
			new User("01", 1, "dddd", 99.99),
			new User("30", 30, "ffff", 889.99)
	);
	
	/**
	 * @Description: 外部迭代
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年11月19日 上午11:47:35
	 */
	@Test
	public void test2() {
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();
			System.out.println(user);
		}
	}
	
	/**
	 * 内部迭代：迭代操作 Stream API 内部完成
	 * @Description: 筛选与切片 
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年11月19日 上午11:36:54
	 */
	@Test
	public void test3() {
		// 所有的中间操作在没有执行终止操作时是不会做任何的处理
		Stream<User> stream = users.stream()
							       .filter((user) -> {
								       System.out.println("测试中间操作...");
								       return user.getAge() > 15;
							       });
		                           
		//只有当做终止操作时，所有的中间操作会一次性的全部执行，称为“惰性求值”
		stream.forEach(System.out::println);
	}
	
	
	/**
	 * @Description: limit 的短路 --> 找够需要的就不在继续
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年11月19日 上午11:51:00
	 */
	@Test
	public void test4() {
		users.stream()
			 .filter((e) -> 
			 {
				 System.out.println("短路测试");
				 return e.getMoney() > 100d;
			 })
			 .limit(2)
			 .forEach(System.out::println);
	}
	
	/**
	 * @Description: skip(n) --- 每一个都会检测过滤, 将 n 个符合过滤条件的舍去
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年11月19日 下午4:08:59
	 */
	@Test
	public void test5() {
		users.stream()
		 .filter((e) -> 
		 {
			 System.out.println("Test");
			 return e.getMoney() > 100d;
		 })
		 .skip(1)
		 .forEach(System.out::println);
	}
	
	/**
	 * @Description: distinct 去重 --- 需要重写 hashCode 和 equals 方法
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年11月19日 下午4:14:02
	 */
	@Test
	public void test6() {
		users.stream()
			 .distinct()
			 .forEach(System.out::println);
	}
	
/**
 * 映射
 * @Description: map -- 接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
 * 				 flatMap -- 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流  
 * @Warning:
 * @Author: DreamLi
 * @Date: 2017年11月19日 下午4:21:05
 */
@Test
public void test7() {
	users.stream()
		 .map(User::getAddress)
		 .forEach(System.out::println);
	
	System.out.println("--------------------------------------");
	
	List<String> strList = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
	
	Stream<Stream<Character>> map = strList.stream()
		 .map(StreamTest1::filterStr);
	map.forEach((stream) -> {
		stream.forEach(System.out::println);
	});
	
	System.out.println("--------------------------------------");

	Stream<Character> flatMap = strList.stream().flatMap(StreamTest1::filterStr);
	flatMap.forEach(System.out::println);
	
	
}

public static Stream<Character> filterStr(String str) {
	List<Character> chars = new ArrayList<>();
	for (Character character : str.toCharArray()) {
		chars.add(character);
	}
	
	return chars.stream();
}
	
	/**
	 * @Description: sorted 排序 -- sorted(): 自然排序  sorted(Comparator com): 定制排序 
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年11月19日 下午4:37:33
	 */
	@Test
	public void test8() {
		users.stream()
			 .map(User::getAge)
			 .sorted()
			 .forEach(System.out::println);
		
		System.out.println("============================");
		
		users.stream()
			 .sorted((u1, u2) -> Integer.compare(u1.getAge(), u2.getAge()))
			 .forEach(System.out::println);
	}
}
