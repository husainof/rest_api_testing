package org.husainof.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {

    @JsonProperty("name")
    private String name;

    @JsonProperty("weight")
    private int weight;

    @JsonProperty("abilities")
    private List<AbilityData> abilities;

    public List<AbilityData> getAbilities() {
        return abilities;
    }

    public int getWeight() {
        return weight;
    }

    public List<String> getAbilityNames() {
        List<String> abilityNames = new ArrayList<>();
        for (AbilityData abilityData : this.getAbilities()) {
            String name = abilityData.getAbility().getName();
            abilityNames.add(name);
        }
        return abilityNames;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Ability{
    @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class AbilityData{
    @JsonProperty("ability")
    private Ability ability;

    public Ability getAbility() {
        return ability;
    }
}



