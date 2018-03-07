package com.royalty.repository;

import java.util.List;

import com.royalty.model.Episode;
import com.royalty.model.Studio;

public interface RoyaltyRepository {
	
	List<Studio> getAllStudios();
	List<Episode> getAllEpisodes();
	
	
}
