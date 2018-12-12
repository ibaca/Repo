package com.royalty.repository;

import com.royalty.model.Episode;
import com.royalty.model.Studio;
import com.royalty.utils.UtilsJSON;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class RoyaltyRepositoryImpl implements RoyaltyRepository {

    @Override
    public List<Studio> getAllStudios() {
        return UtilsJSON.getStudiosfromJSON();
    }

    @Override
    public List<Episode> getAllEpisodes() {
        return UtilsJSON.getEpisodesfromJSON();
    }
}
