package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class PaymentHystrixController {

	@Autowired
	private PaymentHystrixService paymentHystrixService;

	@GetMapping("/consumer/payment/hystrix/ok/{id}")
	public String paymentInfo_OK(@PathVariable("id") Integer id){
		String result = paymentHystrixService.paymentInfo_OK(id);
		return result;
	}


	@GetMapping("/consumer/payment/hystrix/timeout/{id}")
//	@HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
//			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="500")
//	})
	@HystrixCommand
	public String paymentInfo_TimeOut(Integer id){
		int timeNumber = 3;
		try {
			TimeUnit.SECONDS.sleep(timeNumber);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "线程池: " + Thread.currentThread().getName() + " paymentInfo_TimeOut 我是80, id: " + id + "/t" + "耗时间 " + timeNumber;
	}

	public String paymentInfo_TimeOutHandler(Integer id){
		return "线程池: " + Thread.currentThread().getName() + " paymentInfo_TimeOutHandler 我是80, id: " + id + "/t" + "┭┮﹏┭┮";
	}

	public String payment_Global_FallbackMethod(){
		return "Golbal 异常";
	}
}
