package model.cart;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Cart{

	@JsonProperty("cartItems")
	private List<CartItemsItem> cartItems;

	public List<CartItemsItem> getCartItems(){
		return cartItems;
	}
}