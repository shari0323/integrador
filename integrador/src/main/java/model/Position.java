package model;

import jakarta.persistence.*;

@Entity
@Table(name = "cargo")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cargo")
    private Long id;

    @Column(name = "denominacion", nullable = false, length = 100)
    private String name;

    @Column(name = "codigo", nullable = false)
    private String code;

    @Column(name = "grado", nullable = false)
    private String grade;

    @ManyToOne
    @JoinColumn(name = "id_dependencia")
    private Dependency dependency;
    
    // Getters y setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Dependency getDependency() {
        return dependency;
    }

    public void setDependency(Dependency dependency) {
        this.dependency = dependency;
    }

    @Override
    public String toString() {
        return name;
    }
}
