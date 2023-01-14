package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * @author 唐帅七
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
public class PaymentHystrixMain8001 {
	public static void main(String[] args) {
		SpringApplication.run(PaymentHystrixMain8001.class, args);
	}
}
