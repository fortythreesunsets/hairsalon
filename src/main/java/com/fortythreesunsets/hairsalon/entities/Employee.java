package com.fortythreesunsets.hairsalon.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40, name = "first_name")
    private String firstName;

    @Column(nullable = false, length = 40, name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(nullable = true, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String nss;

    @Column(nullable = false, unique = true)
    private String rfc;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", unique = true, foreignKey = @ForeignKey(name = "fk_employee_address"))
    private Address address;

    // Asociaciones
    @OneToMany(mappedBy = "customer")
    private List<Appointment> appointments = new ArrayList<>();

    // Constructores
    public Employee() {}

    public Employee(Long id, String firstName, String lastName, LocalDate birthDate, String email, String nss, String rfc) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.nss = nss;
        this.rfc= rfc;
    }

    // Métodos
    public Long getId() {
        return id;
    }

    public Employee setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Employee setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Employee setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Employee setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Employee setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getNss() {
        return nss;
    }

    public Employee setNss(String nss) {
        this.nss = nss;
        return this;
    }

    public String getRfc() {
        return rfc;
    }

    public Employee setRfc(String rfc) {
        this.rfc = rfc;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public Employee setAddress(Address address) {
        this.address = address;
        return this;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", nss='" + nss + '\'' +
                ", rfc'" + rfc + '\'' +
                '}';
    }
}
