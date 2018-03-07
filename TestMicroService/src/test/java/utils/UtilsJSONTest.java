package utils;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.royalty.model.Episode;
import com.royalty.model.Studio;
import com.royalty.utils.UtilsJSON;

public class UtilsJSONTest {
	
	@Test
	public void testGetAllEpisodes(){
		 List<Episode> result = UtilsJSON.getEpisodesfromJSON();
		 assertNotNull(result);
	}
	@Test
	public void testGetAllStudio(){
		List<Studio> result = UtilsJSON.getStudiosfromJSON();
		assertNotNull(result);
	}
    
}
