package com.royalty.server;

import com.royalty.server.model.Episode;
import com.royalty.server.model.Viewing;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ViewingService {
    @Autowired RoyaltyRepository repository;

    public List<Viewing> createViewing(String episodeId, String customer) {
        Episode episode = repository.getEpisode(episodeId);
        List<Viewing> viewings = repository.ensureViewing(episode);
        viewings.add(new Viewing(episode, customer));
        return viewings;
    }

    public List<Viewing> getAllViewing() {
        return repository.getViewing().collect(Collectors.toList());
    }

    public void resetViewing() {
        repository.resetViewing();
    }
}
