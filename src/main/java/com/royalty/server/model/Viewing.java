package com.royalty.server.model;

import java.io.Serializable;

public class Viewing implements Serializable {
    public Episode episode;
    public int viewings;

    public Viewing(Episode episode) {
        this.episode = episode;
        this.viewings = viewings + 1;
    }

    public boolean hasEpisodeAndId() {
        return (this.episode != null && this.episode.id != null);
    }
}
