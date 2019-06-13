/**
 * 
 */
package com.training.mjunction.product.composite.data.documents;

import java.util.Currency;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Sandip.Adak
 *
 */

@Data
@Accessors(fluent = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "id", "currency", "amount" })
public class Price {
	
	@JsonProperty("id")
	private String id = UUID.randomUUID().toString();

	@JsonProperty("currency")
	private Currency currency;
	
	@JsonProperty("amount")
	private double amount;
}
