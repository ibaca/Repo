package com.royalty.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.royalty.server.model.Episode;
import com.royalty.server.model.Viewing;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ViewingServiceTest {
    @InjectMocks ViewingService viewingService;
    @Spy RoyaltyRepository royaltyRepository;

    @Test public void getAllViewingsNoEpisodes() {
        when(royaltyRepository.getEpisodes()).thenReturn(new ArrayList<>());
        List<Viewing> result = viewingService.getAllViewing();

        assertNotNull(result);
        assertEquals(result.size(), 0);
    }

    @Test public void getAllViewingsWithEpisodes() {
        when(royaltyRepository.getEpisodes()).thenReturn(createEpisodeList());
        List<Viewing> result = viewingService.getAllViewing();

        assertNotNull(result);
        assertEquals(result.size(), 2);
//        assertEquals(result.get(0).viewings, 1);
    }

    @Test public void getIncrementViewing() {
        when(royaltyRepository.getEpisodes()).thenReturn(createEpisodeList());
        List<Viewing> result = viewingService.createViewing("episode1", "customer1");

        assertNotNull(result);
        assertEquals(result.size(), 1);
//        assertEquals(result.get(0).viewings, 2);
    }

    @Test public void getResetViewing() {
        when(royaltyRepository.getEpisodes()).thenReturn(createEpisodeList());
        viewingService.resetViewing();

//        assertNotNull(result);
//        assertEquals(result.size(), 2);
//        assertEquals(result.get(0).viewings, 0);
    }

    @Test(expected = ServiceException.class) public void testAllViewingsWithException() {
        doThrow(new ArrayIndexOutOfBoundsException()).when(royaltyRepository).getEpisodes();
        viewingService.getAllViewing();
    }

    private List<Episode> createEpisodeList() {
        return Arrays.asList(
                createEpisode("episode1", "Game of Thrones S1:E1", "studio1"),
                createEpisode("episode2", "Billions S1:E2", "studio3"));
    }

    private Episode createEpisode(String id, String name, String rightsowner) {
        Episode episode = new Episode();
        episode.id = id;
        episode.name = name;
        episode.rightsowner = rightsowner;
        return episode;
    }
}
