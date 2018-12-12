package utils;

import static org.junit.Assert.assertNotNull;

import com.royalty.model.Episode;
import com.royalty.model.Studio;
import com.royalty.utils.UtilsJSON;
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
