package com.royalty.server.model;

public class Viewing {
    public final Episode episode;
    public final String customer;
    public Viewing(Episode episode, String customer) {
        this.episode = episode;
        this.customer = customer;
    }
}
