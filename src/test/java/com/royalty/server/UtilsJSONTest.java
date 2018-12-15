package com.royalty.server;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class UtilsJSONTest {

    @Test public void testGetAllEpisodes() {
        assertNotNull(UtilsJSON.getEpisodes());
    }

    @Test public void testGetAllStudio() {
        assertNotNull(UtilsJSON.getStudios());
    }
}
