package com.royalty.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.royalty.api.RoyaltyPayment;
import com.royalty.server.model.Episode;
import com.royalty.server.model.Studio;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class PaymentServiceTest {

    @InjectMocks
    PaymentService paymentService;

    @Mock
    RoyaltyRepository royaltyRepository;

    @Test
    public void testAllPaymentsNoStudios() {
        when(royaltyRepository.getAllStudios()).thenReturn(emptyListStudio());

        List<RoyaltyPayment> result = paymentService.getAllPayments();

        assertNotNull(result);
        assertEquals(result.size(), 0);
    }

    @Test
    public void testAllPaymentsNoEpisodes() {
        when(royaltyRepository.getAllStudios()).thenReturn(createStudioList());
        when(royaltyRepository.getAllEpisodes()).thenReturn(emptyListEpisode());

        List<RoyaltyPayment> result = paymentService.getAllPayments();

        assertNotNull(result);
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).viewings, 0);
        assertEquals(result.get(0).royalty, 0.0, .1);
    }

    @Test
    public void testAllPaymentsWithStudiosAndEpisodes() {
        when(royaltyRepository.getAllStudios()).thenReturn(createStudioList());
        when(royaltyRepository.getAllEpisodes()).thenReturn(createEpisodeList());

        List<RoyaltyPayment> result = paymentService.getAllPayments();

        assertNotNull(result);
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).viewings, 1);
        assertEquals(result.get(0).royalty, 12.0, .1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testAllPaymentsWhenException() {
        doThrow(new ArrayIndexOutOfBoundsException()).when(royaltyRepository).getAllStudios();

        paymentService.getAllPayments();
    }

    @Test
    public void testPaymentWhenEstudioAndEpisodio() {
        when(royaltyRepository.getAllStudios()).thenReturn(createStudioList());
        when(royaltyRepository.getAllEpisodes()).thenReturn(createEpisodeList());

        RoyaltyPayment royaltyAfter = paymentService.getPaymentById("studio1");
        assertEquals(royaltyAfter.rightsOwnerId, "studio1");
        assertEquals(royaltyAfter.rightsOwnerName, "HBO");
        assertEquals(royaltyAfter.royalty, 12.0, .1);
        assertEquals(royaltyAfter.viewings, 1, .1);
    }

    private List<Studio> createStudioList() {
        Studio studio1 = createStudio("studio1", "HBO", 12);
        Studio studio2 = createStudio("studio2", "Sky UK", 14.67);

        return Arrays.asList(studio1, studio2);
    }

    private Studio createStudio(String id, String name, double payment) {
        Studio studio = new Studio();
        studio.id = id;
        studio.name = name;
        studio.payment = payment;
        return studio;
    }

    private List<Episode> createEpisodeList() {
        Episode episode1 = createEpisode("episode1", "Game of Thrones S1:E1", "studio1");
        Episode episode2 = createEpisode("episode2", "Billions S1:E2", "studio3");

        return Arrays.asList(episode1, episode2);
    }

    private Episode createEpisode(String id, String name, String rightsowner) {
        Episode episode = new Episode();
        episode.id = id;
        episode.name = name;
        episode.rightsOwnerId = rightsowner;
        return episode;
    }

    private List<Studio> emptyListStudio() {
        return new ArrayList<>();
    }

    private List<Episode> emptyListEpisode() {
        return new ArrayList<>();
    }
}
