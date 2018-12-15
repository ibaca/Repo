package com.royalty.server;

import static java.util.stream.Collectors.toList;

import com.royalty.api.RoyaltyPayment;
import com.royalty.server.model.Studio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {
    @Autowired RoyaltyRepository repository;

    public List<RoyaltyPayment> getAllPayments() {
        return repository.getAllStudios().stream().map(this::getStudioPayment).collect(toList());
    }

    public RoyaltyPayment getPaymentById(String id) {
        return getStudioPayment(repository.getAllStudios().stream().filter(p -> id.equals(p.id)).findFirst().get());
    }

    private RoyaltyPayment getStudioPayment(Studio studio) {
        long viewings = repository.getAllEpisodes().stream().filter(e -> studio.id.equals(e.rightsOwnerId)).count();
        return new RoyaltyPayment(studio.id, studio.name, studio.payment * viewings, (int) viewings);
    }
}
