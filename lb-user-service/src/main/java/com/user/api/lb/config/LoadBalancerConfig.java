package com.user.api.lb.config;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

public class LoadBalancerConfig {

	@Bean
	public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(
			ConfigurableApplicationContext context) {
		System.out.println("Configuring Load balancer to prefer same instance");
		return ServiceInstanceListSupplier.builder()				
				//.withBlockingDiscoveryClient()
				.withDiscoveryClient()
				.build(context);
	}
}
