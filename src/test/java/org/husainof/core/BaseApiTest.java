package org.husainof.core;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.husainof.utils.ConfigProvider;


public class BaseApiTest {

    protected final RequestSpecification requestSpec =  new RequestSpecBuilder()
            .setBaseUri(ConfigProvider.readConfig().getString("baseUrl"))
            .setBasePath(ConfigProvider.readConfig().getString("basePath"))
            .build();
}
