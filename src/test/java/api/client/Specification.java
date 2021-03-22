package api.client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.Arrays;

import static api.filter.LogFilter.filters;
import static config.WebDriverConfigHelper.getBaseUrl;
import static io.qameta.allure.Allure.addAttachment;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.in;

public class Specification {

    public static ResponseSpecification getResponseSpecificationJson() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(is(in(Arrays.asList(201, 200))))
                .build();
    }

    public static RequestSpecification getRequestSpecification(String baseUrl, String path) {
        return new RequestSpecBuilder()
                .addFilter(filters().withCustomTemplates())
                .setBaseUri(baseUrl)
                .setBasePath(path)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static RequestSpecification getRequestSpecification() {
        return getRequestSpecification(getBaseUrl(), "api/");
    }

    public static void addAttachmentToRequest(String methodRequest, String nameRequest, Response response) {
        addAttachment("Time execution: " + methodRequest + " " + nameRequest, response.getTimeIn(MILLISECONDS) + " Milliseconds");
    }
}
