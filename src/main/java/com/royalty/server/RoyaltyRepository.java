package com.royalty.server;

import com.royalty.server.model.Episode;
import com.royalty.server.model.Studio;
import com.royalty.server.model.Viewing;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;

@Component
public class RoyaltyRepository {
    private final Map<String, Studio> studios;
    private final Map<String, Episode> episodes;
    private final Map<Episode, List<Viewing>> viewings;

    public RoyaltyRepository() {
        studios = UtilsJSON.getStudios().stream().collect(Collectors.toMap(s -> s.id, Function.identity()));
        episodes = UtilsJSON.getEpisodes().stream().collect(Collectors.toMap(s -> s.id, Function.identity()));
        viewings = new HashMap<>();
    }
    public Collection<Studio> getStudios() { return studios.values(); }

    public Collection<Episode> getEpisodes() { return episodes.values(); }

    public Episode getEpisode(String id) {
        Episode episode = episodes.get(id);
        if (episode == null) throw new NoSuchElementException("episode " + id + " not found");
        return episode;
    }

    public Stream<Viewing> getViewing() {
        return viewings.values().stream().flatMap(Collection::stream);
    }

    public List<Viewing> ensureViewing(Episode episode) {
        return viewings.computeIfAbsent(episode, key -> new LinkedList<>());
    }

    public void resetViewing() {
        viewings.clear();
    }
}
