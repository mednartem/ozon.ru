package model.cart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseAddToCart{

	@JsonProperty("success")
	private boolean success;

	@JsonProperty("cart")
	private Cart cart;

	public boolean isSuccess(){
		return success;
	}

	public Cart getCart(){
		return cart;
	}
}