package hibernate.queries;

import hibernate.model.Anime;
import hibernate.model.MainCharacter;
import hibernate.model.Manga;
import hibernate.model.Studio;
import hibernate.model.Watchers;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;

import javax.enterprise.inject.Typed;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class Queries {

    EntityManager entityManager;

    public Queries(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Anime> animesByType(String type) {
        TypedQuery<Anime> query = entityManager.createQuery(
                "SELECT a FROM Anime a WHERE a.type = :type", Anime.class);
        return query.setParameter("type", type).getResultList();
    }

    public List<MainCharacter> mainCharactersByType(String type) {
        TypedQuery<MainCharacter> query = entityManager.createQuery(
                "SELECT mc FROM MainCharacter mc WHERE mc.type = :type", MainCharacter.class);
        return query.setParameter("type", type).getResultList();
    }

    public List<Manga> mangasReleasedBefore(DateTime year) {
        TypedQuery<Manga> query = entityManager.createQuery(
                "SELECT m FROM Manga m WHERE m.release < :year", Manga.class);
        return query.setParameter("year", year).getResultList();
    }

    public List<Studio> studiosByCity(String city) {
        TypedQuery<Studio> query = entityManager.createQuery(
                "SELECT s FROM Studio s WHERE s.city = :city", Studio.class);
        return query.setParameter("city", city).getResultList();
    }

    public List<Watchers> watchersOlderThan(int age) {
        TypedQuery<Watchers> query = entityManager.createQuery(
                "SELECT w FROM Watchers w WHERE w.age > :age", Watchers.class);
        return query.setParameter("age", age).getResultList();
    }
}
