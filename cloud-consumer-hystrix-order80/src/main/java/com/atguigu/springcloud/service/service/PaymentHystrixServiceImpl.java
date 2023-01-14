package com.atguigu.springcloud.service.service;

import com.atguigu.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Service;

@Service
public class PaymentHystrixServiceImpl implements PaymentHystrixService {
	@Override
	public String paymentInfo_OK(Integer id) {
		return "ssssss";
	}

	@Override
	public String paymentInfo_TimeOut(Integer id) {
		return "xxxxx";
	}
}
