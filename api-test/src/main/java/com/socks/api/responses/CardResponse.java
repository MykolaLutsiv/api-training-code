package com.socks.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CardResponse{

	@JsonProperty("id")
	private String id;

	public String getId(){
		return id;
	}
}