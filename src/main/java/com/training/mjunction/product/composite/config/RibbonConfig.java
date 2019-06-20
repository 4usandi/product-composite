/**
 * 
 */
package com.training.mjunction.product.composite.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.netflix.client.config.DefaultClientConfigImpl;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;

@Configuration
@RibbonClient(name = "product-catalog", configuration = RibbonConfig.class)
public class RibbonConfig {

	@Bean
	public IClientConfig ribbonClientConfig() {
		return DefaultClientConfigImpl.getClientConfigWithDefaultValues("product-catalog",
				DefaultClientConfigImpl.DEFAULT_PROPERTY_NAME_SPACE);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public IPing ribbonPing(IClientConfig config) {
		PingUrl pingUrl = new PingUrl();
		pingUrl.setPingAppendString("/actuator/info");
		return pingUrl;
	}

	@Bean
	public IRule ribbonRule(IClientConfig config) {
		return new AvailabilityFilteringRule();
	}

	/*
	 * @Configuration public static class ProductCatalogRibbonConfig {
	 * 
	 * }
	 */

}
