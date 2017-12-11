package com.inetsoft.stream;

import java.util.concurrent.RecursiveTask;

/**
 * @Description: 使用 Fork/Join 框架计算 0 -- 10000000000L 的和
 * 		RecursiveTask: 有返回值的接口 protected abstract V compute(); 
 * 		RecursiveAction: 没有返回值的接口 protected abstract void compute(); 
 * @Warning: 
 * @Author DreamLi
 * @Package Java8  --  com.inetsoft.stream.CalculateSumTask
 * @Date: 2017年12月3日 下午4:53:05
 * @Version: 1.0.0
 */
public class CalculateSumTask extends RecursiveTask<Long> {
	private static final long serialVersionUID = 113216515415412L;
	private Long start;
	private Long end;
	
	public CalculateSumTask(Long start, Long end) {
		this.start = start;
		this.end = end;
	}

	private static Long CRITICAL_NUMBER = 10000L;
	
	@Override
	protected Long compute() {
		Long len = end - start;
		if(len < CRITICAL_NUMBER) {
			Long sum = 0L;
			for (long i = start; i <= end; i++) {
				sum += i;
			}

			return sum;
		} else {
			Long mid = (start + end) / 2;
			CalculateSumTask left = new CalculateSumTask(start, mid);
			left.fork();
			CalculateSumTask right = new CalculateSumTask(mid + 1, end);
			
			return right.compute() + left.join();
		}
	}

}
