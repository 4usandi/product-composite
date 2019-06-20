package com.training.mjunction.product.composite.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.training.mjunction.product.composite.clients.ProductClient;
import com.training.mjunction.product.composite.data.documents.Product;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class ProductController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired ProductClient productClient;

	@RequestMapping(method = RequestMethod.GET, value = "/api/v1/products/{name}")
	public Product findByName(@PathVariable("name") final String name) {
		log.info(String.format("name(%s)", name));

		return null;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/api/v1/products/{category}/{name}")
	public Product findByCategoryName(@PathVariable("category") final String category,
			@PathVariable("name") final String name) {
		log.info(String.format("name(%s) category(%s)", name, category));
		return null;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/api/v1/products")
//	@HystrixCommand(fallbackMethod = "getProducts")
	public List<Product> findAll(@RequestHeader("Authorization") String auth) {
		log.info("Product.findAll()");
//		final ResponseEntity<List<Product>> response = restTemplate.exchange("http://product-catalog/api/v1/products",
//				HttpMethod.GET, new HttpEntity<>(createHeaders(auth)),
//				new ParameterizedTypeReference<List<Product>>() {
//				});
//
//		return response.getBody();
		return productClient.getProducts(auth);
	}

	HttpHeaders createHeaders(String auth) {
		return new HttpHeaders() {
			private static final long serialVersionUID = 1L;

			{
				set("Authorization", auth);
			}
		};
	}
	
	/*HttpHeaders createHeaders(String username, String password){
		   return new HttpHeaders() {{
		         String auth = username + ":" + password;
		         byte[] encodedAuth = Base64.encodeBase64( auth.getBytes(Charset.forName("US-ASCII")) );
		         String authHeader = "Basic " + new String( encodedAuth );
		         set( "Authorization", authHeader );
		      }};
	}*/

	@RequestMapping(method = RequestMethod.PUT, value = "/api/v1/products")
	public Product add(@RequestBody final Product product) {
		log.info(String.format("Product.add(%s)", product));
		return null;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/api/v1/products/{name}")
	public Product update(@PathVariable("name") final String name, @RequestBody final Product product) {

		log.info(String.format("Product.add(%s)", product));

		final Product dbOne = null;

		if (null == dbOne) {
			throw new IllegalArgumentException("Product not found name  :" + name);
		}

		return null;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/api/v1/products/{name}")
	public void delete(@PathVariable("name") final String name) {

		log.info(String.format("Product.delete(%s)", name));

		final Product dbOne = null;

		if (null == dbOne) {
			throw new IllegalArgumentException("Product not found name  :" + name);
		}
	}

}
