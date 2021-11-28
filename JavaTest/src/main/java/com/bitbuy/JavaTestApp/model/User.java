package com.bitbuy.JavaTestApp.model;

import java.util.Objects;

public class User {

	@Override
	public String toString() {
		return "User [uuid=" + uuid + ", name=" + name + ", email=" + email + ", phone=" + phone + "]";
	}
	private String uuid;
	private String name;
	private String email;
	private String phone;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public int hashCode() {
		return Objects.hash(email, name, phone, uuid);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(name, other.name)
				&& Objects.equals(phone, other.phone) && Objects.equals(uuid, other.uuid);
	}
	
}
