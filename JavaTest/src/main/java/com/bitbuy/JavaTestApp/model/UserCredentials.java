package com.bitbuy.JavaTestApp.model;

import java.util.Objects;

public class UserCredentials {
	
	private String username;
	private String password;
	
	public String getUserName() {
		return username;
	}
	
	public void setUserName(String userName) {
		this.username = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserCredentials other = (UserCredentials) obj;
		return Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
