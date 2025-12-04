package com.univer.mediamanager.repository.media.tvshow;

import com.univer.mediamanager.model.media.TVShow;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TVShowCustomRepositoryImpl implements TVShowCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TVShow> findTVShowsWithCrew() {
        return entityManager.createQuery(
                        "SELECT DISTINCT t FROM TVShow t " +
                                "LEFT JOIN FETCH t.directors " +
                                "LEFT JOIN FETCH t.actors " +
                                "WHERE t.directors IS NOT EMPTY", TVShow.class)
                .getResultList();
    }

    @Override
    public List<TVShow> findByCrewMemberName(String name) {
        return entityManager.createQuery(
                        "SELECT DISTINCT t FROM TVShow t " +
                                "LEFT JOIN t.directors d " +
                                "LEFT JOIN t.actors a " +
                                "WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
                                "OR LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%'))", TVShow.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public List<TVShow> findLongestTVShows(int minRuntime) {
        return entityManager.createQuery(
                        "SELECT t FROM TVShow t WHERE t.runtime >= :minRuntime ORDER BY t.runtime DESC", TVShow.class)
                .setParameter("minRuntime", minRuntime)
                .getResultList();
    }
}