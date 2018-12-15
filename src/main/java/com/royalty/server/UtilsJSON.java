package com.royalty.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.royalty.server.model.Episode;
import com.royalty.server.model.Studio;
import java.io.IOException;
import java.util.List;

public class UtilsJSON {

    public static List<Studio> getStudios() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(UtilsJSON.class.getResourceAsStream("studios.json"), Studios.class).studios;
        } catch (IOException e) {
            throw new RuntimeException("ups! fatal error loading studios!", e);
        }
    }

    public static List<Episode> getEpisodes() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(UtilsJSON.class.getResourceAsStream("episodes.json"), Episodes.class).episodes;
        } catch (IOException e) {
            throw new RuntimeException("ups! fatal error loading episodes!", e);
        }
    }

    static class Episodes {
        public List<Episode> episodes;
    }

    static class Studios {
        public List<Studio> studios;
    }
}
