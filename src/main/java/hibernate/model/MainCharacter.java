package hibernate.model;
import javax.persistence.*;

@Entity
@Table(name = "MainCharacter", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})})
public class MainCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "mainCharacter_seq_gen")
    @SequenceGenerator(name = "mainCharacter_seq_gen", sequenceName = "mainCharacter_seq")
    @Column(name = "id_mainCharacter")
    private int id_mainCharacter;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private String type;

    public MainCharacter() {}

    public int getId() {
        return id_mainCharacter;
    }

    public void setId( int id ) {
        this.id_mainCharacter = id;
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

    public void setType( String type ) {
        this.type = type;
    }
}
