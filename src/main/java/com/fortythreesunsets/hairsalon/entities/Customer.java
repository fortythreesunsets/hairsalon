package com.fortythreesunsets.hairsalon.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40, name = "first_name")
    private String firstName;

    @Column(nullable = false, length = 40, name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    //@JsonIgnore ignorará la llamada a appointments
    @JsonIgnoreProperties(value = {"customer"})  // Que JSON ignore el customer contenido en appointment
    @OneToMany(mappedBy = "customer")
    private List<Appointment> appointments = new ArrayList<>();

    // Constructores
    public Customer() {}

    public Customer(Long id, String firstName, String lastName, String email, String phone, LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
    }

    // Métodos
    public Long getId() {
        return id;
    }

    public Customer setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Customer setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Customer setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Customer setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Customer setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Customer setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public Customer setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
        return this;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

}
