package com.inetsoft.stream;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import com.inetsoft.stream.User.Status;

/**
 * @Description: 终止操作 
 * @Warning: 
 * @Author DreamLi
 * @Package Java8  --  com.inetsoft.stream.StreamTest3
 * @Date: 2017年11月19日 下午7:08:13
 * @Version: 1.0.0
 */
public class StreamTest3 {
	
	private List<User> users = Arrays.asList(
			 new User("看见你", 81, "aaaa", 999.99, Status.BUSY), 
			 new User("快哦不会吧", 56, "cccc", 1119.99, Status.FREE),
			 new User("快哦怕看", 19, "bbbb", 89.99, Status.VOCATION), 
			 new User("人太多", 55, "dddd", 99.99, Status.BUSY), 
			 new User("哦看就", 11, "dddd", 99.99, Status.BUSY),
			 new User("色认识", 30, "ffff", 889.99, Status.VOCATION)
			 );
	
	/**
	 * @Description: 归约
	 * 	reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。 
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年11月19日 下午7:08:29
	 */
	@Test
	public void test1() {
		List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);
		Optional<Integer> sum1 = list.stream()
			.reduce((x, y) -> x + y);
		System.out.println(sum1.get());
		
		Optional<Integer> reduce = users.stream()
			 .map(User::getAge)
			 .reduce(Integer::sum);
		System.out.println(reduce.get());
	}
	
	/**
	 * @Description: 搜索名字中有 '哦' 的 User
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年11月19日 下午8:03:37
	 */
	@Test
	public void test2() {
		Optional<Integer> reduce = users.stream()
			 .map(User::getName)
			 .flatMap(StreamTest1::filterStr)
			 .map((ch) -> {
	//				 System.out.println(ch);
				 if(ch == '哦') {
					 return 1;
				 } else {
					return 0;
				 }
			 })
			 .reduce(Integer::sum);
		
		System.out.println(reduce.get());
	}
	
	/**
	 * @Description: collect 收集 -- 将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法 
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年11月19日 下午8:50:57
	 */
	@Test
	public void test3() {
		List<String> list = users.stream()
			 .map(User::getName)
			 .collect(Collectors.toList());
		list.forEach(System.out::println);
		
		System.out.println("------------------------------");
		
		Set<String> set = users.stream()
			 .map(User::getName)
			 .collect(Collectors.toSet());
		set.forEach(System.out::println);
		
		System.out.println("------------------------------");
		
		HashSet<String> hashSet = users.stream()
			 .map(User::getName)
			 .collect(Collectors.toCollection(HashSet::new));
		hashSet.forEach(System.out::println);
	}
	
	/**
	 * @Description: Collectors 常用方法 
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年11月19日 下午9:12:36
	 */
	@Test
	public void test4() {
		Optional<Integer> max = users.stream()
			 .map(User::getAge)
			 .collect(Collectors.maxBy(Integer::compare));
		System.out.println(max.get());
		
		System.out.println("------------------------------");
		
		Optional<Double> min = users.stream()
			 .map(User::getMoney)
			 .collect(Collectors.minBy(Double::compare));
		System.out.println(min.get());
		
		System.out.println("------------------------------");
		
		Double suming = users.stream()
			 .collect(Collectors.summingDouble(User::getMoney));
		System.out.println(suming);
		
		System.out.println("------------------------------");
		
		Double avg = users.stream()
			 .collect(Collectors.averagingDouble(User::getMoney));
		System.out.println(avg);
		
		System.out.println("------------------------------");
		
		Long count = users.stream()
				.collect(Collectors.counting());
		System.out.println(count);
		
		System.out.println("------------------------------");
		
		DoubleSummaryStatistics tool = users.stream()
			 .collect(Collectors.summarizingDouble(User::getMoney));
		System.out.println(tool.getSum());
		System.out.println(tool.getAverage());
		System.out.println(tool.getCount());
		
		System.out.println("------------------------------");
	}
	
	/**
	 * @Description: 分组 
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年11月19日 下午9:40:13
	 */
	@Test
	public void test5() {
		Map<Status, List<User>> map = users.stream()
			 .collect(Collectors.groupingBy(User::getStatus));
		System.out.println(map);
	}
	
	/**
	 * @Description: 多级分组 
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年11月19日 下午9:44:01
	 */
	@Test
	public void test6() {
		Map<Status, Map<String, List<User>>> collect = users.stream()
			 .collect(Collectors.groupingBy(User::getStatus, Collectors.groupingBy((u) -> {
				 if(u.getAge() > 60) {
					 return "老年";
				 } else if(u.getAge() > 40) {
					 return "中年";
				 } else {
					 return "青年";
				 }
			 })));
		System.out.println(collect);
	}
	
	/**
	 * @Description: 分区 -- 分为 true 和 false 两组
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年11月19日 下午9:49:40
	 */
	@Test
	public void test7() {
		Map<Boolean, List<User>> map = users.stream()
			 .collect(Collectors.partitioningBy((u) -> u.getAge() > 50));
		System.out.println(map);
	}
	
	/**
	 * @Description: joinging 
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年11月19日 下午9:57:00
	 */
	@Test
	public void test8() {
		String collect = users.stream()
			 .map(User::getName)
			 .collect(Collectors.joining(", ", "<<<", ">>>"));
		System.out.println(collect);
	}
	
	/**
	 * @Description: reducing 
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年11月19日 下午10:00:49
	 */
	@Test
	public void test9() {
		Optional<Double> sum = users.stream()
			 .map(User::getMoney)
			 .reduce(Double::sum);
		System.out.println(sum.get());
	}
	
}
