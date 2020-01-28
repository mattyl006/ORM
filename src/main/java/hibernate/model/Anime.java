package hibernate.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Anime")

public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "anime_seq_gen")
    @SequenceGenerator(name = "anime_seq_gen", sequenceName = "anime_seq")
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "manga", referencedColumnName = "id_manga")
    private Manga manga;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "mainCharacter", referencedColumnName = "id_mainCharacter")
    private MainCharacter mainCharacter;

    @ManyToOne(fetch = FetchType.LAZY)
    private Studio studio;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Watchers> watchers = new ArrayList<>();

    public Anime(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Manga getManga() {
        return manga;
    }

    public void setManga(Manga manga) {
        this.manga = manga;
    }

    public MainCharacter getMainCharacter() {
        return mainCharacter;
    }

    public void setMainCharacter(MainCharacter mainCharacter) {
        this.mainCharacter = mainCharacter;
    }

    public List<Watchers> getWatchers() {
        return watchers;
    }
}
