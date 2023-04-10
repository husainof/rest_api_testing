package org.husainof.tests;

import io.qameta.allure.Description;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.husainof.core.BaseApiTest;
import org.husainof.models.Pokemon;
import org.husainof.models.PokemonList;
import org.husainof.utils.ConfigProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;


public class ApiTest extends BaseApiTest {

    @Test
    @Description("Проверка логики данных покемонов: вес rattata больше, чем у pidgeotto.")
    public void checkWeightComparison() {

        Pokemon rattata =  RestAssured.given(requestSpec)
                    .filter(new AllureRestAssured())
                .when()
                    .get("rattata")
                .then()
                    .statusCode(200)
                    .body("name", equalTo("rattata"))
                    .extract()
                    .as(Pokemon.class);

        Pokemon pidgeotto =  RestAssured.given(requestSpec)
                    .filter(new AllureRestAssured())
                .when()
                    .get("pidgeotto")
                .then()
                    .statusCode(200)
                    .body("name", equalTo("pidgeotto"))
                    .extract()
                    .as(Pokemon.class);

        Assert.assertTrue(rattata.getWeight() < pidgeotto.getWeight());
    }

    @Test
    @Description("Проверка логики данных покемонов: у rattata есть способность 'run-away'.")
    public void checkHasAbility() {

        Pokemon rattata =  RestAssured.given(requestSpec)
                .filter(new AllureRestAssured())
                .when()
                .get("rattata")
                .then()
                .statusCode(200)
                .body("name", equalTo("rattata"))
                .extract()
                .as(Pokemon.class);
                
        Assert.assertTrue(rattata.getAbilityNames().contains("run-away"));
    }

    @DataProvider(name = "limits")
    public Object[] getLimits() {
        return new Object[]{10, 20, 30};
    }

    @Test(dataProvider = "limits")
    @Description("Проверка ограниченности списка покемонов.")
    public void checkPageLimit(int limit) {
        RestAssured.given(requestSpec)
                .filter(new AllureRestAssured())
                .params("limit", limit)
                .params("offset", 0)
                .when()
                .get()
                .then()
                .statusCode(200)
                .body("results", hasSize(limit));
    }

    @Test
    @Description("Проверка существование их имён покемонов.")
    public void checkPokemonNameExist() {

        PokemonList pokemonList =  RestAssured.given(requestSpec)
                    .filter(new AllureRestAssured())
                    .params("limit", 10)
                    .params("offset", 0)
                .when()
                    .get()
                .then()
                    .statusCode(200)
                    .extract()
                    .as(PokemonList.class);

        Assert.assertTrue(pokemonList.getPokemonNames().stream().allMatch(name -> !name.isEmpty()));
    }
}
