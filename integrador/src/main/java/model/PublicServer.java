package model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "servidor_publico")
public class PublicServer {
    
   @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id_servidor")
private Long id;

@Column(name = "cedula", unique = true)
private String idNumber;

@Column(name = "nombres")
private String firstName;

@Column(name = "apellidos")
private String lastName;

@Column(name = "fecha_nacimiento")
private LocalDate birthDate;

@Column(name = "genero")
private String gender;

@Column(name = "estado_civil")
private String maritalStatus;

@Column(name = "tipo_sangre")
private String bloodType;

@Column(name = "correo_personal")
private String personalEmail;

@Column(name = "correo_institucional")
private String institutionalEmail;

@Column(name = "telefono")
private String phone;

@Column(name = "tipo_vinculacion")
private String employmentType;

@ManyToOne
@JoinColumn(name = "id_cargo")
private Position position;

@ManyToOne
@JoinColumn(name = "id_dependencia")
private Dependency dependency;
    public PublicServer(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getInstitutionalEmail() {
        return institutionalEmail;
    }

    public void setInstitutionalEmail(String institutionalEmail) {
        this.institutionalEmail = institutionalEmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }
}
