package com.royalty.server;

import com.royalty.server.model.Episode;
import com.royalty.server.model.Viewing;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ViewingService {

    @Autowired RoyaltyRepository royaltyRepository;

    public synchronized List<Viewing> createViewing(String idEpisode, String idCustomer) throws ServiceException {
        try {
            return getAllViewing().stream()
                    .filter(elem -> elem.hasEpisodeAndId())
                    .filter(elem -> elem.episode.id.equals(idEpisode)).map(elem -> incrementViewings(elem))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new ServiceException();
        }
    }

    private Viewing incrementViewings(Viewing viewing) {
        viewing.viewings = viewing.viewings + 1;
        return viewing;
    }

    public synchronized List<Viewing> resetViewing() throws ServiceException {
        try {
            return getAllViewing().stream().map(elem -> resetViewings(elem)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException();
        }

    }

    private Viewing resetViewings(Viewing viewing) {
        viewing.viewings = 0;
        return viewing;
    }

    public List<Viewing> getAllViewing() throws ServiceException {
        List<Viewing> result;
        try {
            List<Episode> episodes = royaltyRepository.getAllEpisodes();
            result = episodes.stream().map(elem -> new Viewing(elem)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException();
        }
        return result;
    }
}
