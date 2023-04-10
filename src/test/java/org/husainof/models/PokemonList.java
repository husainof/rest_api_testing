package org.husainof.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonList {

    @JsonProperty("results")
    private List<PokemonTuple> pokemonList;

    public List<PokemonTuple> getPokemonList() {
        return pokemonList;
    }

    public List<String> getPokemonNames() {
        List<String> pokemonNames = new ArrayList<>();
        for (PokemonTuple pokemonTuple : this.getPokemonList()) {
            String name = pokemonTuple.getName();
            pokemonNames.add(name);
        }
        return pokemonNames;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class PokemonTuple{
    @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }
}
