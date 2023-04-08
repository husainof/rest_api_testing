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
    @Description("Проверка логики данных покемонов: вес rattata больше, чем у pidgeotto; у rattata есть способность 'run-away'.")
    public void checkPokemonDataLogic() {

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
        Assert.assertTrue(rattata.getAbilityNames().contains("run-away"));
    }

    @Test
    @Description("Проверка ограниченности списка покемонов и существование их имён.")
    public void checkPokemonNameExist() {

        Integer limit = ConfigProvider.readConfig().getInt("limit");

        PokemonList pokemonList =  RestAssured.given(requestSpec)
                    .filter(new AllureRestAssured())
                    .params("limit", limit)
                    .params("offset", 0)
                .when()
                    .get()
                .then()
                    .statusCode(200)
                    .body("results.name", hasSize(limit))
                    .extract()
                    .as(PokemonList.class);

        Assert.assertTrue(pokemonList.getPokemonNames().stream().allMatch(name -> !name.isEmpty()));
    }
}
