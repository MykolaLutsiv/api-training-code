package com.socks.api.responses.CardsResponse;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Embedded{

	@JsonProperty("card")
	private List<CardItem> card;

	public List<CardItem> getCard(){
		return card;
	}
}