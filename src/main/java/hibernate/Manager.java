package hibernate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import hibernate.model.Watchers;
import hibernate.model.Studio;
import hibernate.model.Manga;
import hibernate.model.Anime;
import hibernate.model.MainCharacter;
import hibernate.queries.Queries;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.File;
import java.util.List;
import java.io.IOException;


class Manager {

    public static void main(String[] args) {

        System.out.println("Start");
        EntityManager entityManager = null;
        EntityManagerFactory entityManagerFactory = null;
        DateTime d = new DateTime();

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-dynamic");
            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();
            Manga mg = new Manga();
            mg.setName("Tennis no Ouji");
            d = d.withDate(1999, 1, 1);
            mg.setRelease(d);
            entityManager.persist(mg);
            entityManager.getTransaction().commit();
            System.out.println("Manga added.");

            entityManager.getTransaction().begin();
            Manga mg2 = new Manga();
            mg2.setName("Bleach");
            d = d.withDate(2002, 1, 1);
            mg2.setRelease(d);
            entityManager.persist(mg2);
            entityManager.getTransaction().commit();
            System.out.println("Manga added.");

            entityManager.getTransaction().begin();
            MainCharacter mc = new MainCharacter();
            mc.setName("Rukia");
            mc.setType("Tsundere");
            entityManager.persist(mc);
            entityManager.getTransaction().commit();
            System.out.println("Character added.");

            entityManager.getTransaction().begin();
            MainCharacter mc2 = new MainCharacter();
            mc2.setName("Echizen Ryoma");
            mc2.setType("Eiji");
            entityManager.persist(mc2);
            entityManager.getTransaction().commit();
            System.out.println("Character added.");

            entityManager.getTransaction().begin();
            Studio s = new Studio();
            s.setName("Pierrot");
            s.setCity("Tokyo");
            d.withDate(1979,1,1);
            s.setFoundation(d);
            entityManager.persist(s);
            entityManager.getTransaction().commit();
            System.out.println("Studio added.");

            entityManager.getTransaction().begin();
            Studio s2 = new Studio();
            s2.setName("Trans Arts");
            s2.setCity("Tokyo");
            d.withDate(1975,1 ,1);
            s2.setFoundation(d);
            entityManager.persist(s2);
            entityManager.getTransaction().commit();
            System.out.println("Studio added.");

            entityManager.getTransaction().begin();
            Watchers w = new Watchers();
            w.setName("Mateusz");
            w.setSurname("Tylka");
            w.setAge(20);
            entityManager.persist(w);
            entityManager.getTransaction().commit();
            System.out.println("Watcher added.");

            entityManager.getTransaction().begin();
            Watchers w2 = new Watchers();
            w2.setName("Marcin");
            w2.setSurname("Linusowski");
            w2.setAge(40);
            entityManager.persist(w2);
            entityManager.getTransaction().commit();
            System.out.println("Watcher added.");

            entityManager.getTransaction().begin();
            Anime a = new Anime();
            a.setName("Prince of tennis");
            a.setType("Sport");
            entityManager.persist(a);
            entityManager.getTransaction().commit();
            System.out.println("Anime added.");

            entityManager.getTransaction().begin();
            Anime a2 = new Anime();
            a2.setName("Bleach");
            a2.setType("Shounen");
            entityManager.persist(a2);
            entityManager.getTransaction().commit();
            System.out.println("Anime added.");

            entityManager.getTransaction().begin();
            for(int i = 0; i < 12; i++) {
                entityManager.persist(Watchers.copyWatcher(w));
            }
            entityManager.getTransaction().commit();

            entityManager.getTransaction().begin();
            Queries query = new Queries(entityManager);
            List<Anime> animes = query.animesByType("Shounen");
            for(Anime i : animes) {
                System.out.println(i.getName() + " " + i.getType());
            }
            entityManager.getTransaction().commit();

            entityManager.getTransaction().begin();
            query = new Queries(entityManager);
            List<MainCharacter> mainCharacters = query.mainCharactersByType("Tsundere");
            for(MainCharacter i : mainCharacters) {
                System.out.println(i.getName() + " " + i.getType());
            }
            entityManager.getTransaction().commit();

            entityManager.getTransaction().begin();
            query = new Queries(entityManager);
            d = d.withDate(2010,1 ,1);
            List<Manga> mangas = query.mangasReleasedBefore(d);
            for(Manga i : mangas) {
                System.out.println(i.getName() + " " + i.getRelease());
            }
            entityManager.getTransaction().commit();

            entityManager.getTransaction().begin();
            query = new Queries(entityManager);
            List<Studio> studios = query.studiosByCity("Tokyo");
            for(Studio i : studios) {
                System.out.println(i.getName() + " " + i.getCity() + " " + i.getFoundation());
            }
            entityManager.getTransaction().commit();

            entityManager.getTransaction().begin();
            query = new Queries(entityManager);
            List<Watchers> watchers = query.watchersOlderThan(18);
            for(Watchers i : watchers) {
                System.out.println(i.getName() + " " + i.getSurname() + " " + i.getAge());
            }
            entityManager.getTransaction().commit();

            //JSON and XML Time

//            Query query2 = entityManager.createQuery("SELECT w FROM Watchers w");
//            List<Watchers> watchers2 = query2.getResultList();
//            watchersToJson(watchers2);
//            watchersToXml(watchers2);
//
//            Query query3 = entityManager.createQuery("SELECT m FROM Manga m");
//            List<Manga> mangas2 = query3.getResultList();
//            mangaToJson(mangas2);
//
//            entityManager.getTransaction().begin();
//            ObjectMapper mapper = new ObjectMapper();
//            List<MainCharacter> charactersJSON = mapper.readValue
//                    (new File("Characters.json"), new TypeReference<List<MainCharacter>>() {});
//            for (MainCharacter i : charactersJSON) {
//                entityManager.persist(i);
//            }
//            entityManager.getTransaction().commit();
//
//            entityManager.getTransaction().begin();
//            XmlMapper xmlMapper = new XmlMapper();
//            List<MainCharacter> charactersXML = xmlMapper.readValue
//                    (new File("Characters.xml"), new TypeReference<List<MainCharacter>>() {});
//            for (MainCharacter i : charactersXML) {
//                entityManager.persist(i);
//            }
//            entityManager.getTransaction().commit();

            entityManager.close();

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory create fail." + ex);
        } finally {
            entityManagerFactory.close();
        }
    }

    public static void watchersToJson(List<Watchers> watchers) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(new File("Watchers.json"), watchers);
    }

    public static void mangaToJson(List<Manga> mangas) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JodaModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(new File("Manga.json"), mangas);
    }

    public static void watchersToXml(List<Watchers> watchers) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        xmlMapper.writeValue(new File("Watchers.xml"), watchers);
    }

}