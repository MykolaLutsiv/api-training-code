package com.socks.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CardNotFoundResponse{

	@JsonProperty("status_code")
	private int statusCode;

	@JsonProperty("status_text")
	private String statusText;

	@JsonProperty("error")
	private String error;

	public int getStatusCode(){
		return statusCode;
	}

	public String getStatusText(){
		return statusText;
	}

	public String getError(){
		return error;
	}
}