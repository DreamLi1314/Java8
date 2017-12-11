package com.inetsoft.newfeatures;

/**
 * @Description: Java8 接口的新特性
 *   1.8以前接口中只能声明常量(public static final)和抽象方法(public abstract T methodName(prams);)
 *   1.8开始接口中可以声明具有默认实现的方法和有实现的静态方法
 * @Warning: 如果一个类实现了一个接口并且实现了一个具有和接口默认方法同名的类,使用 "类优先" 原则调用类中的方法
 * 			 如果一个类实现的两个接口中具有相同的默认方法, 那么该子类必须覆盖这个方法
 * @Author DreamLi
 * @Package Java8  --  com.inetsoft.newfeatures.MyFunc1
 * @Date: 2017年12月3日 下午9:14:59
 * @Version: 1.0.0
 */
public interface MyFunc1 {
	
	/**
	 * @Description: 带有默认实现的方法 
	 * @Warning:
	 * @Author: DreamLi
	 * @Date: 2017年12月3日 下午9:18:54
	 * @return
	 */
	default String getName() {
		return "哈哈";
	}
	
	/**
	 * @Description: 静态方法 
	 * @Warning: 不能为抽象的
	 * @Author: DreamLi
	 * @Date: 2017年12月3日 下午9:19:17
	 */
	public static void printHell() {
		System.out.println("Hello World!");
	}
	
}
