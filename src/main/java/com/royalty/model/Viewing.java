package com.royalty.model;

import java.io.Serializable;

public class Viewing implements Serializable {
    private Episode episode;
    private int viewings;

    public Viewing(Episode episode) {
        this.setEpisode(episode);
        this.setViewings(this.getViewings() + 1);
    }

    public Episode getEpisode() {
        return episode;
    }

    public void setEpisode(Episode episode) {
        this.episode = episode;
    }

    public int getViewings() {
        return viewings;
    }

    public void setViewings(int viewings) {
        this.viewings = viewings;
    }

    public boolean hasEpisodeAndId() {
        return (this.episode != null && this.episode.getId() != null);
    }
}
