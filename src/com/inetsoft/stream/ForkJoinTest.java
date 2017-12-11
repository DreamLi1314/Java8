package com.inetsoft.stream;

import java.time.Duration;
import java.time.Instant;
import java.util.OptionalLong;
import java.util.stream.LongStream;

import org.junit.Test;

public class ForkJoinTest {

	/**
	 * @Description: 测试使用 Fork/Join 框架计算 0 -- 10000000000L 的和 
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年12月3日 下午4:51:29
	 */
	@Test
	public void test01() {
		Instant start = Instant.now();
		
		CalculateSumTask calculateSumTask = new CalculateSumTask(0L, 10000000000L);
		Long sum = calculateSumTask.compute();
		System.out.println(sum);
		
		Instant end = Instant.now();
		
		System.out.println("Excute Time: " + Duration.between(start, end).toMillis());//53549
	}
	
	@Test
	public void test02() {
		Instant start = Instant.now();
		Long sum = 0L;
		for(long i = 0L; i <= 10000000000L; i++) {
			sum += i;
		}
		
		System.out.println(sum);
	
		Instant end = Instant.now();
		
		System.out.println("Excute Time: " + Duration.between(start, end).toMillis());//86001
	}
	
	/**
	 * @Description: 使用 Java8 的并行流计算
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年12月3日 下午5:42:43
	 */
	@Test
	public void test03() {
		Instant start = Instant.now();
		
		OptionalLong sum = LongStream.rangeClosed(0L, 10000000000L)
				  .parallel()
				  .reduce(Long::sum);

		System.out.println(sum.getAsLong());

		Instant end = Instant.now();

		System.out.println("Excute Time: " + Duration.between(start, end).toMillis());//11617
	}
	
}
