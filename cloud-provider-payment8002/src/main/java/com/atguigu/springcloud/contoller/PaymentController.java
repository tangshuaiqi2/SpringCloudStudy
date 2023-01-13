package com.atguigu.springcloud.contoller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@Value("${server.port}")
	public String serverPort;

	@PostMapping("/payment/create")
	public CommonResult create(@RequestBody Payment payment){
		int result = paymentService.create(payment);
		log.info("******插入结果:" + result);
		if(result > 0){
			return new CommonResult(200, "插入成功" + serverPort);
		}
		return new CommonResult(444, "error", null);
	}

	@GetMapping("/payment/get/{id}")
	public CommonResult getPaymentByid(@PathVariable("id") Long id){
		Payment result = paymentService.getPaymentByid(id);
		log.info("******插入结果:" + result);
		if(result != null){
			return new CommonResult(200, "查询成功" + serverPort, result);
		}
		return new CommonResult(444, "error id: " + id, null);
	}

	@GetMapping("/payment/lb")
	public String getPaymentLb(){
		return serverPort;
	}
}
