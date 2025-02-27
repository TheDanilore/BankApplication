package com.devsu.hackerearth.backend.account.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Account extends Base {
    private String number;
    private String type;
    private double initialAmount;
    private boolean isActive;

    @Column(name = "client_id")
    private Long clientId;

    public Account(Long id, String number, String type, double initialAmount, boolean isActive, Long clientId) {
        super(id);
        this.number = number;
        this.type = type;
        this.initialAmount = initialAmount;
        this.isActive = isActive;
        this.clientId = clientId;
    }

    public Account(String number, String type, double initialAmount, boolean isActive, Long clientId) {
        this.number = number;
        this.type = type;
        this.initialAmount = initialAmount;
        this.isActive = isActive;
        this.clientId = clientId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(double initialAmount) {
        this.initialAmount = initialAmount;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
