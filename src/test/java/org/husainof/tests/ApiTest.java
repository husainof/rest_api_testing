package org.husainof.tests;

import groovy.json.JsonParser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.husainof.core.BaseApiTest;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.util.List;

import static org.hamcrest.Matchers.*;

public class ApiTest extends BaseApiTest {

    @Test
    public void checkPokemonDataLogic() {
        Response response =  requestSpecification
                .when()
                    .get("rattata")
                .then()
                    .statusCode(200)
                    .log().all()
                    .body("name", Matchers.equalTo("rattata"))
                    .extract()
                    .response();
    }

    @Test
    public void checkLimitPokemonData() {
        Integer limit = 10;

        requestSpecification
                    .params("limit", limit)
                    .params("offset", 0)
                .when()
                    .get()
                .then()
                    .statusCode(200)
                    .body("results", hasSize(limit));
    }

    @Test
    public void checkPokemonNameExist() {
        Integer limit = 10;

        Response response =  requestSpecification
                .params("limit", limit)
                .params("offset", 0)
                .when()
                .get()
                .then()
                .statusCode(200)
                .body("results.name", hasSize(limit))
                .extract()
                .response();

        JsonPath jsonPath = response.jsonPath();
        List<String> pokemonNameList = jsonPath.get("results.name");

        Assert.assertTrue(pokemonNameList.stream().allMatch(x -> !x.isEmpty()));
    }
}
