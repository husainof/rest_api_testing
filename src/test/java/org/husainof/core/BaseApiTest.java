package org.husainof.core;

import io.restassured.specification.RequestSpecification;
import org.husainof.utils.ConfigProvider;

import static io.restassured.RestAssured.given;


public class BaseApiTest {

    protected final RequestSpecification requestSpecification = given()
            .baseUri(ConfigProvider.readConfig().getString("baseUrl"))
            .basePath(ConfigProvider.readConfig().getString("basePath"));
}
