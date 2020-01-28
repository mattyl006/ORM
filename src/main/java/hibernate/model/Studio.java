package hibernate.model;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Studio", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})})
public class Studio {
    @Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "studio_gen_seq")
    @SequenceGenerator(name = "studio_gen_seq", sequenceName = "studio_seq")
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "city", nullable = false)
    private String city;

    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy")
    private DateTime foundation;

    @OneToMany(mappedBy = "studio", fetch = FetchType.LAZY)
    private List<Anime> animes = new ArrayList<>();

    public Studio(){}

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public DateTime getFoundation() {
        return foundation;
    }

    public void setFoundation(DateTime foundation) {
        this.foundation = foundation;
    }

    public List<Anime> getAnimes() {
        return animes;
    }

    public void setAnimes(List<Anime> animes) {
        this.animes = animes;
    }
}
