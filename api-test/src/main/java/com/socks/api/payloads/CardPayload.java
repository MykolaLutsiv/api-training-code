package com.socks.api.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


@Getter
@Setter
@ToString
@Accessors(fluent = true)
public class CardPayload {

    @JsonProperty("expires")
    private String expires;

    @JsonProperty("longNum")
    private String longNum;

    @JsonProperty("ccv")
    private String ccv;

    @JsonProperty("userID")
    private String userID;
}