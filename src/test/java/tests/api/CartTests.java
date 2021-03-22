package tests.api;

import api.client.Cart;
import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import model.cart.CartItemsItem;
import model.cart.ResponseAddToCart;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Cart")
@Tag("api")
@Owner("Demyshev")
public class CartTests {

    @Test
    void test001checkAddToCart() {
        CartItemsItem cartItems = new CartItemsItem();
        cartItems.setId(146506788);
        cartItems.setQty(1);

        ResponseAddToCart addToCart = new Cart().addToCart(Collections.singletonList(cartItems));

        assertEquals(cartItems.getId(), addToCart.getCart().getCartItems().get(0).getId());

    }
}
