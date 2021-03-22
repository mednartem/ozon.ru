package api.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.cart.ResponseAddToCart;

import static api.client.Specification.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class Cart {

    @Step
    public ResponseAddToCart addToCart(Object body) {
        Response response = given().log().all()
                .spec(getRequestSpecification())
                .body(body)
                .post("/composer-api.bx/_action/addToCart");

        addAttachmentToRequest("POST", "/composer-api.bx/_action/addToCart", response);

        return response
                .then().log().all()
                .spec(getResponseSpecificationJson())
                .body("success", equalTo(true))
                .extract().as(ResponseAddToCart.class);
    }
}
