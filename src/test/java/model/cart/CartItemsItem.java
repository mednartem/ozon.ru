package model.cart;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CartItemsItem{

	@JsonProperty("qty")
	private int qty;

	@JsonProperty("id")
	private int id;

	public int getQty(){
		return qty;
	}

	public int getId(){
		return id;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public void setId(int id) {
		this.id = id;
	}
}