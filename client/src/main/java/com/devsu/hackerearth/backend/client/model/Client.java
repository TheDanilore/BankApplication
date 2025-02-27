package com.devsu.hackerearth.backend.client.model;

import javax.persistence.Entity;

@Entity
public class Client extends Person {
	private String password;
	private boolean isActive;

	public Client(Long id, String name, String dni, String gender, int age, String address, String phone,
			String password, boolean isActive) {
		super(id, name, dni, gender, age, address, phone);
		this.password = password;
		this.isActive = isActive;
	}

	public Client(String name, String dni, String gender, int age, String address, String phone, String password,
			boolean isActive) {
		super(name, dni, gender, age, address, phone);
		this.password = password;
		this.isActive = isActive;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
