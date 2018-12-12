package com.royalty.server;

import static org.junit.Assert.assertNotNull;

import com.royalty.server.model.Episode;
import com.royalty.server.model.Studio;
import com.royalty.server.UtilsJSON;
import java.util.List;
import org.junit.Test;

public class UtilsJSONTest {

    @Test
    public void testGetAllEpisodes() {
        List<Episode> result = UtilsJSON.getEpisodesfromJSON();
        assertNotNull(result);
    }
    @Test
    public void testGetAllStudio() {
        List<Studio> result = UtilsJSON.getStudiosfromJSON();
        assertNotNull(result);
    }
}
