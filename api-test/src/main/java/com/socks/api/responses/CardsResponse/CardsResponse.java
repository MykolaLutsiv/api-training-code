package com.socks.api.responses.CardsResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CardsResponse{

	@JsonProperty("_embedded")
	private Embedded embedded;

	public Embedded getEmbedded(){
		return embedded;
	}
}