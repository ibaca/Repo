package com.royalty.server.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Studio implements Serializable {
    private String id;
    private String name;
    private BigDecimal payment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }
}
