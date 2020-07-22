package com.socks.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.Generated;

@Getter
@Setter
@ToString
@Generated("com.robohorse.robopojogenerator")
public class CardCreateResponse {

    @JsonProperty("id")
    private String id;
}