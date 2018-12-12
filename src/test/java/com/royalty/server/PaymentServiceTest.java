package com.royalty.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.royalty.server.model.Episode;
import com.royalty.api.RoyaltyPayment;
import com.royalty.server.model.Studio;
import com.royalty.server.RoyaltyRepository;
import com.royalty.server.PaymentService;
import com.royalty.server.ServiceException;
import java.math.BigDecimal;
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
    public void testAllPaymentsNoStudios() throws ServiceException {
        when(royaltyRepository.getAllStudios()).thenReturn(emptyListStudio());

        List<RoyaltyPayment> result = paymentService.getAllPayments();

        assertNotNull(result);
        assertEquals(result.size(), 0);
    }

    @Test
    public void testAllPaymentsNoEpisodes() throws ServiceException {
        when(royaltyRepository.getAllStudios()).thenReturn(createStudioList());
        when(royaltyRepository.getAllEpisodes()).thenReturn(emptyListEpisode());

        List<RoyaltyPayment> result = paymentService.getAllPayments();

        assertNotNull(result);
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getViewings(), 0);
        assertEquals(result.get(0).getRoyaltiy(), "0.0 £");
    }

    @Test
    public void testAllPaymentsWithStudiosAndEpisodes() throws ServiceException {
        when(royaltyRepository.getAllStudios()).thenReturn(createStudioList());
        when(royaltyRepository.getAllEpisodes()).thenReturn(createEpisodeList());

        List<RoyaltyPayment> result = paymentService.getAllPayments();

        assertNotNull(result);
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getViewings(), 1);
        assertEquals(result.get(0).getRoyaltiy(), "12.0 £");
    }

    @Test(expected = ServiceException.class)
    public void testAllPaymentsWhenException() throws ServiceException {
        doThrow(new ArrayIndexOutOfBoundsException()).when(royaltyRepository).getAllStudios();

        paymentService.getAllPayments();
    }

    @Test
    public void testPaymentWhenEstudioAndEpisodio() throws ServiceException {
        when(royaltyRepository.getAllStudios()).thenReturn(createStudioList());
        when(royaltyRepository.getAllEpisodes()).thenReturn(createEpisodeList());

        RoyaltyPayment royaltyAfter = paymentService.getPaymentById("studio1");
        assertEquals(royaltyAfter.getRightsownerId(), "studio1");
        assertEquals(royaltyAfter.getRightsowner(), "HBO");
        assertEquals(royaltyAfter.getRoyaltiy(), "12.0 £");
        assertEquals(royaltyAfter.getViewings(), 1);
    }

    private List<Studio> createStudioList() {
        Studio studio1 = createStudio("studio1", "HBO", new BigDecimal(12));
        Studio studio2 = createStudio("studio2", "Sky UK", new BigDecimal(14.67));

        return Arrays.asList(studio1, studio2);
    }

    private Studio createStudio(String id, String name, BigDecimal payment) {
        Studio studio = new Studio();
        studio.setId(id);
        studio.setName(name);
        studio.setPayment(payment);
        return studio;
    }

    private List<Episode> createEpisodeList() {
        Episode episode1 = createEpisode("episode1", "Game of Thrones S1:E1", "studio1");
        Episode episode2 = createEpisode("episode2", "Billions S1:E2", "studio3");

        return Arrays.asList(episode1, episode2);
    }

    private Episode createEpisode(String id, String name, String rightsowner) {
        Episode episode = new Episode();
        episode.setId(id);
        episode.setName(name);
        episode.setRightsowner(rightsowner);
        return episode;
    }

    private List<Studio> emptyListStudio() {
        return new ArrayList<>();
    }

    private List<Episode> emptyListEpisode() {
        return new ArrayList<>();
    }
}
