package com.socks.api.responses.CardsResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CardItem{

	@JsonProperty("expires")
	private String expires;

	@JsonProperty("longNum")
	private String longNum;

	@JsonProperty("_links")
	private Links links;

	@JsonProperty("ccv")
	private String ccv;

	@JsonProperty("id")
	private String id;

	public String getExpires(){
		return expires;
	}

	public String getLongNum(){
		return longNum;
	}

	public Links getLinks(){
		return links;
	}

	public String getCcv(){
		return ccv;
	}

	public String getId(){
		return id;
	}
}