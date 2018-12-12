package com.royalty.api;

import com.royalty.server.model.Studio;
import java.io.Serializable;

public class RoyaltyPayment implements Serializable {
    private String rightsownerId;
    private String rightsowner;
    private String royaltiy;
    private int viewings;

    public RoyaltyPayment() {}

    public RoyaltyPayment(Studio studio) {
        this.rightsownerId = studio.getId();
        this.rightsowner = studio.getName();
        this.royaltiy = studio.getPayment().toString();
    }

    public String getRightsownerId() {
        return rightsownerId;
    }

    public void setRightsownerId(String rightsownerId) {
        this.rightsownerId = rightsownerId;
    }

    public String getRightsowner() {
        return rightsowner;
    }

    public void setRightsowner(String rightsowner) {
        this.rightsowner = rightsowner;
    }

    public String getRoyaltiy() {
        return royaltiy != null ? royaltiy + " £" : "0 £";
    }

    public void setRoyaltiy(String royaltiy) {
        this.royaltiy = royaltiy;
    }

    public int getViewings() {
        return viewings;
    }

    public void setViewings(int viewings) {
        this.viewings = viewings;
    }

    public void incrementRoyalty() {
        this.royaltiy = Double.toString(Double.parseDouble(this.royaltiy) * this.viewings);
    }
}
