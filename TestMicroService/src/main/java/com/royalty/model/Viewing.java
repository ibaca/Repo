package com.royalty.model;

import java.io.Serializable;

public class Viewing implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Episode episode;
	
	private Integer viewings=0;
	
	public Viewing(Episode episode) {		
		this.setEpisode(episode);
		this.setViewings(this.getViewings()+1);		
	}

	/**
	 * @return the episode
	 */
	public Episode getEpisode() {
		return episode;
	}

	/**
	 * @param episode the episode to set
	 */
	public void setEpisode(Episode episode) {
		this.episode = episode;
	}
	
	/**
	 * @return the viewings
	 */
	public Integer getViewings() {
		return viewings;
	}

	/**
	 * @param viewings the viewings to set
	 */
	public void setViewings(Integer viewings) {
		this.viewings = viewings;
	}
	
	/**
	 * Check episode is not null and its Id is not null
	 * @return
	 */
	public boolean hasEpisodeAndId() {
		return (this.episode!=null && this.episode.getId()!=null);
	}
}
