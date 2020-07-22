package com.socks.api.responses.CardsResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Links{

	@JsonProperty("self")
	private Self self;

	@JsonProperty("card")
	private Card card;

	public Self getSelf(){
		return self;
	}

	public Card getCard(){
		return card;
	}
}