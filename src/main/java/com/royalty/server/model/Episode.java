package com.royalty.server.model;

import java.io.Serializable;

public class Episode implements Serializable {

    private String id;

    private String name;

    private String rightsowner;

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

    public String getRightsowner() {
        return rightsowner;
    }

    public void setRightsowner(String rightsowner) {
        this.rightsowner = rightsowner;
    }
}
