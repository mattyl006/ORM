package hibernate.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Watchers")
public class Watchers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "watchers_seq_gen")
    @SequenceGenerator(name = "watchers_seq_gen", sequenceName = "watchers_seq")
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "age")
    private int age;

    @ManyToMany(mappedBy = "watchers", fetch = FetchType.LAZY)
    private List<Anime> animes = new ArrayList<>();

    public Watchers() {}

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname( String surname ) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge( int age ) {
        this.age = age;
    }

    public static Watchers copyWatcher(Watchers watch) {
        Watchers person = new Watchers();
        person.setSurname(watch.getSurname());
        person.setName(watch.getName());
        person.setAge(watch.getAge());
        return person;
    }
}
