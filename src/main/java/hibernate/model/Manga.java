package hibernate.model;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


@Entity
@Table(name = "Manga", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})})
public class Manga {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "manga_seq_gen")
    @SequenceGenerator(name = "manga_seq_gen", sequenceName = "manga_seq")
    @Column(name = "id_manga")
    private int id_manga;

    @Column(name = "name", nullable = false)
    private String name;

    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy")
    private DateTime release;

    public Manga() {}

    public int getId() {
        return id_manga;
    }

    public void setId(int id) {
        this.id_manga = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DateTime getRelease() {
        return release;
    }

    public void setRelease(DateTime release) {
        this.release = release;
    }
}
