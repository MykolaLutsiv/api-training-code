package com.socks.api.responses.CardsResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Self{

	@JsonProperty("href")
	private String href;

	public String getHref(){
		return href;
	}
}