package model;

import java.io.Serializable;

public class Token implements Serializable {
	private final String rawResponse;

	private final String secret;

	private final String token;

	public Token(String token, String secret) {
		this(token, secret, null);
	}

	public Token(String token, String secret, String rawResponse) {
		this.token = token;
		this.secret = secret;
		this.rawResponse = rawResponse;
	}

	public String getToken() {
		return token;
	}

	public String getSecret() {
		return secret;
	}

	public String getRawResponse() {
		return rawResponse;
	}

}