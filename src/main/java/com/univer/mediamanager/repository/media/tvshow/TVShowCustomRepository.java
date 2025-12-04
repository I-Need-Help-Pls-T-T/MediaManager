package com.univer.mediamanager.repository.media.tvshow;

import com.univer.mediamanager.model.media.TVShow;
import java.util.List;

public interface TVShowCustomRepository {

    List<TVShow> findTVShowsWithCrew();

    List<TVShow> findByCrewMemberName(String name);

    List<TVShow> findLongestTVShows(int minRuntime);
}