package com.kalpix.downloader.backend.utils;

import java.io.Serializable;
import java.util.UUID;

public class JwtRequest implements Serializable {

	private static final Long serialVersionUID = 5926468583005150707L;

	private String username;
	private String password;

	//default constructor for JSON Parsing
	public JwtRequest()
	{
	}

	public JwtRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}