/**
 * 
 */
package com.training.mjunction.product.composite.clients;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.training.mjunction.product.composite.data.documents.Product;

/**
 * @author Sandip.Adak
 *
 */

@FeignClient(name = "product-catalog", configuration = ProductClientConfiguration.class, fallback = ProductClientFallback.class)
public interface ProductClient {
	
	@RequestMapping(value="/api/v1/products", method=RequestMethod.GET)
	List<Product> getProducts(@RequestHeader("Authorization") String auth);
	
	

}

@Configuration  // here configuration only be loaded into ApplicationContext corresponding to the contextId
class ProductClientConfiguration {

  @Bean
  public ProductClientFallback productClientFallback(){
    return new ProductClientFallback();
  }

}

class ProductClientFallback implements ProductClient {

	@Override
	public List<Product> getProducts(@RequestHeader("Authorization") String auth) {
		// Service is down return empty list of return from cache
		System.err.println("### In ProductClientFallback.getProducts()");
	    return new ArrayList<Product>();
	}
}
