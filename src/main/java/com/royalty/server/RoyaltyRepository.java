package com.royalty.server;

import com.royalty.server.model.Episode;
import com.royalty.server.model.Studio;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class RoyaltyRepository {

    public List<Studio> getAllStudios() {
        return UtilsJSON.getStudiosfromJSON();
    }

    public List<Episode> getAllEpisodes() {
        return UtilsJSON.getEpisodesfromJSON();
    }
}
