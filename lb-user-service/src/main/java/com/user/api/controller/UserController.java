package com.user.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.user.api.lb.ProductService;
import com.user.api.output.ProductInfo;
import com.user.api.output.UserInfo;
import com.user.api.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	ProductService lbProductService;

	@Autowired
	RestTemplate restTemplate;

	@GetMapping(value = "", produces = { "application/json" })
	public ResponseEntity<List<UserInfo>> getUSerInfo() {

		return new ResponseEntity<>(userService.getUserInfo(), HttpStatus.OK);
	}

	@GetMapping(value = "/rest-template", produces = { "application/json" })
	public ResponseEntity<List<ProductInfo>> getUSerProductInfo() {
		

		String url = "http://product-service/product";

		ResponseEntity<List<ProductInfo>> response = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<ProductInfo>>() {
				});

		List<ProductInfo> productInfo = response.getBody();

		return new ResponseEntity<>(productInfo, HttpStatus.OK);
	}

	@GetMapping(value = "/feign", produces = { "application/json" })
	public ResponseEntity<List<ProductInfo>> getRTProductInfo() {

		List<ProductInfo> pInfoList = lbProductService.getProductInfo();

		return new ResponseEntity<>(pInfoList, HttpStatus.OK);
	}

}
