package model;

import jakarta.persistence.*;

@Entity
@Table(name = "dependencia")
public class Dependency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dependencia")
    private Long id;

    @Column(name = "nombre_dependencia", nullable = false, length = 100)
    private String name;

    @Column(name = "nivel", length = 50)
    private String level;
    

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return name;
    }
}
