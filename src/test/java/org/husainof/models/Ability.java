package org.husainof.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
class Ability{
    @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }
}