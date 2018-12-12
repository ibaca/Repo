package com.royalty.repository;

import com.royalty.model.Episode;
import com.royalty.model.Studio;
import java.util.List;

public interface RoyaltyRepository {

    List<Studio> getAllStudios();
    List<Episode> getAllEpisodes();
}
