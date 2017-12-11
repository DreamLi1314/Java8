package com.inetsoft.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

public class Test3 {

	//test consumer
	@Test
	public void test1() {
		this.consumer("Hello World!", (str) -> System.out.println(str));
	}
	
	private void consumer(String str, Consumer<String> con) {
		con.accept(str);
	}
	
	//test supplier
	@Test
	public void test2() {
		String data = this.supplier(() -> "This is resource..".toUpperCase());
		System.out.println(data);
	}
	
	private String supplier(Supplier<String> sup) {
		return sup.get();
	}
	
	//test function
	@Test
	public void test3() {
		Integer result = this.function(12.5, (param) -> (int) (param * param));
		System.out.println(result);
	}
	
	private Integer function(double param, Function<Double, Integer> func) {
		return func.apply(param);
	}
	
	//test predicate
	@Test
	public void test4() {
		System.out.println(this.isExist(3, (item) -> list.contains(item)));
	}
	
	List<Integer> list = Arrays.asList(1, 2, 3, 4);
	
	private boolean isExist(Integer item, Predicate<Integer> pred) {
		return pred.test(item);
	}
}
