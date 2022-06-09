package com.user.api.lb;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.user.api.output.ProductInfo;

@FeignClient("product-service")
public interface ProductService {

	@GetMapping(value = "/product")
	public List<ProductInfo> getProductInfo();
}
