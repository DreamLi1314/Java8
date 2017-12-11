package com.inetsoft.lambda;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;
/*
 * 一、方法引用：若 Lambda 体中的功能，已经有方法提供了实现，可以使用方法引用
 * 			  （可以将方法引用理解为 Lambda 表达式的另外一种表现形式）
 * 
 * 1. 对象的引用 :: 实例方法名
 * 
 * 2. 类名 :: 静态方法名
 * 
 * 3. 类名 :: 实例方法名
 * 
 * 注意：
 * 	 ①方法引用所引用的方法的参数列表与返回值类型，需要与函数式接口中抽象方法的参数列表和返回值类型保持一致！
 * 	 ②若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： ClassName::MethodName
 * 
 * 二、构造器引用 :构造器的参数列表，需要与函数式接口中参数列表保持一致！
 * 
 * 1. 类名 :: new
 * 
 * 三、数组引用
 * 
 * 	类型[] :: new;
 * 
 * 
 */
public class TestMethodRef {
	
	//1. 对象的引用 :: 实例方法名
	@Test
	public void test1() {
		Consumer<String> con1 = (str) -> System.out.println(str);
		con1.accept("Hello ");
		
		//方法引用的形式
		Consumer<String> con2 = System.out::println;
		con2.accept("World!");
	}
	
	//2. 类名 :: 静态方法名
	@Test
	public void test2() {
		Comparator<Integer> com1 = (x, y) -> Integer.compare(x, y);
		
		Comparator<Integer> com2 = Integer::compare;
	}
	
	//3. 类名 :: 实例方法名
	@Test
	public void test3() {
		BiPredicate<String, String> pred = (str1, str2) -> str1.equals(str2);
		System.out.println(pred.test("test", "test"));
		
		BiPredicate<String, String> pred1 = String::equals;
		System.out.println(pred1.test("test", "test"));
	}
	
	//构造器引用 :构造器的参数列表，需要与函数式接口中参数列表保持一致！
	@Test
	public void test4() {
		Supplier<User> sup1 = () -> new User();
		
		Supplier<User> sup2 = User::new;
	}
	
	//数组引用
	@Test 
	public void test5() {
		Function<Integer, String[]> fun1 = (len) -> new String[len];
		System.out.println(fun1.apply(10).length);
		
		Function<Integer, String[]> fun2 = String[]::new;
		System.out.println(fun2.apply(20).length);
	}
	
	
}
