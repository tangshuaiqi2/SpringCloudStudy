package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
	public String paymentInfo_OK(Integer id){
		return "线程池: " + Thread.currentThread().getName() + " paymentInfo_OK, id: " + id + "/t" + "hahaha~";
	}


	@HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="500")
	})
	public String paymentInfo_TimeOut(Integer id){
		int timeNumber = 3;
		try {
			TimeUnit.SECONDS.sleep(timeNumber);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "线程池: " + Thread.currentThread().getName() + " paymentInfo_TimeOut, id: " + id + "/t" + "耗时间 " + timeNumber;
	}

	public String paymentInfo_TimeOutHandler(Integer id){
		return "线程池: " + Thread.currentThread().getName() + " paymentInfo_TimeOutHandler, id: " + id + "/t" + "┭┮﹏┭┮";
	}

	@HystrixCommand(fallbackMethod = "paymentCircuitBreakerHander",commandProperties = {
			@HystrixProperty(name = "circuitBreaker.enabled",value = "true"), //是否开启断路器
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), //时间窗口期
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "6"),//失败多少次
	})
	/**
	 * 服务熔断
	 */
	public String paymentCircuitBreaker(@PathVariable("id") Integer id){
		if(id < 0){
			throw new RuntimeException("不能负数");
		}
		String s = IdUtil.simpleUUID();

		return Thread.currentThread().getName() + "\t" + "调用成功" + s;
	}

	public String paymentCircuitBreakerHander(@PathVariable("id") Integer id){
		return "id 不能负数" + id;
	}

}
