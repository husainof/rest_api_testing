package org.husainof.tests;

import org.husainof.core.BaseApiTest;
import org.husainof.models.Pokemon;
import org.husainof.models.PokemonList;
import org.husainof.utils.ConfigProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;


@Execution(ExecutionMode.CONCURRENT)
public class ApiTest extends BaseApiTest {

    @Test
    public void checkPokemonDataLogic() {

        Pokemon rattata =  requestSpecification
                .when()
                    .get("rattata")
                .then()
                    .statusCode(200)
                    .body("name", equalTo("rattata"))
                    .extract()
                    .as(Pokemon.class);

        Pokemon pidgeotto =  requestSpecification
                .when()
                    .get("pidgeotto")
                .then()
                    .statusCode(200)
                    .body("name", equalTo("pidgeotto"))
                    .extract()
                    .as(Pokemon.class);

        Assertions.assertTrue(rattata.getWeight() < pidgeotto.getWeight());
        Assertions.assertTrue(rattata.getAbilityNames().contains("run-away"));
    }

    @Test
    public void checkPokemonNameExist() {

        Integer limit = ConfigProvider.readConfig().getInt("limit");

        PokemonList pokemonList =  requestSpecification
                    .params("limit", limit)
                    .params("offset", 0)
                .when()
                    .get()
                .then()
                    .statusCode(200)
                    .body("results.name", hasSize(limit))
                    .extract()
                    .as(PokemonList.class);

        Assertions.assertTrue(pokemonList.getPokemonNames().stream().allMatch(name -> !name.isEmpty()));
    }
}
