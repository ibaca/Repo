package com.royalty.api;

public class RoyaltyPayment {
    public final String rightsOwnerId;
    public final String rightsOwnerName;
    public final double royalty;
    public final int viewings;
    public RoyaltyPayment(String rightsOwnerId, String rightsOwnerName, double royalty, int viewings) {
        this.rightsOwnerId = rightsOwnerId;
        this.rightsOwnerName = rightsOwnerName;
        this.royalty = royalty;
        this.viewings = viewings;
    }
}
