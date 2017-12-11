package com.inetsoft.repeatannotation;

import org.junit.Test;

public class AnnotationTest {
	@Test
	public void test01() throws Exception {
		MyAnnotation[] annotations = AnnotationTest.class.getMethod("show", new Class[] {String.class})
				.getAnnotationsByType(MyAnnotation.class);
		System.out.println(annotations.length);
		for (MyAnnotation myAnnotation : annotations) {
			System.out.println(myAnnotation.value());
		}
	}

	@MyAnnotation("World")
	@MyAnnotation("Hello")
	public void show(@MyAnnotation("Param") String test) {
		
	}
}