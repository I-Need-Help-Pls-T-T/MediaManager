package com.univer.mediamanager.repository.media;

import com.univer.mediamanager.model.media.Movie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

public class MovieCustomRepositoryImpl implements MovieCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Movie> findMoviesWithCrew() {
        return entityManager.createQuery(
                        "SELECT DISTINCT m FROM Movie m " +
                                "LEFT JOIN FETCH m.directors " +
                                "LEFT JOIN FETCH m.actors " +
                                "WHERE m.directors IS NOT EMPTY", Movie.class)
                .getResultList();
    }

    @Override
    public List<Movie> findByCrewMemberName(String name) {
        return entityManager.createQuery(
                        "SELECT DISTINCT m FROM Movie m " +
                                "LEFT JOIN m.directors d " +
                                "LEFT JOIN m.actors a " +
                                "LEFT JOIN m.screenwriters s " +
                                "WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
                                "OR LOWER(a.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
                                "OR LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))", Movie.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public List<Movie> findHighBudgetMovies(double minBudget) {
        return entityManager.createQuery(
                        "SELECT m FROM Movie m WHERE m.budget >= :minBudget ORDER BY m.budget DESC", Movie.class)
                .setParameter("minBudget", minBudget)
                .getResultList();
    }
}