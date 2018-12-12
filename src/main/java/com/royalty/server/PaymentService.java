package com.royalty.server;

import com.royalty.server.model.Episode;
import com.royalty.api.RoyaltyPayment;
import com.royalty.server.model.Studio;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {

    @Autowired RoyaltyRepository royaltyRepository;

    public List<RoyaltyPayment> getAllPayments() throws ServiceException {
        try {
            List<Studio> studios = royaltyRepository.getAllStudios();
            return studios.stream().map(studio -> new RoyaltyPayment(studio))
                    .map(royaltyPayment -> incrementRoyaltyAndViewingsIfExistsEpisode(royaltyPayment))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException();
        }
    }

    private RoyaltyPayment incrementRoyaltyAndViewingsIfExistsEpisode(RoyaltyPayment royaltyPayment) {
        List<Episode> episodes = royaltyRepository.getAllEpisodes();
        episodes.stream().filter(episode -> episode.getRightsowner().equals(royaltyPayment.getRightsownerId()))
                .map(episode -> incrementViewings(royaltyPayment)).collect(Collectors.toList());

        royaltyPayment.incrementRoyalty();
        return royaltyPayment;
    }

    private RoyaltyPayment incrementViewings(RoyaltyPayment payment) {
        payment.setViewings(payment.getViewings() + 1);
        return payment;
    }

    public RoyaltyPayment getPaymentById(String id) throws ServiceException {
        try {
            return getAllPayments().stream().filter(p -> p.getRightsownerId().equals(id)).findAny().get();
        } catch (Exception e) {
            throw new ServiceException();
        }
    }
}
