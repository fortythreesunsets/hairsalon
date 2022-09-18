package com.fortythreesunsets.hairsalon.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="appointments")
public class Appointment implements Serializable {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    private Integer duration;  // en minutos

    @Column(length = 400)
    private String description;

    // Asociaciones
    @JsonIgnoreProperties(value = {"appointments"}) // Ignora atributo específico del Customer
    @ManyToOne
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "fk_appointment_customer"))
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "treatment_id", foreignKey = @ForeignKey(name = "fk_appointment_treatment"))
    private Treatment treatment;

    @ManyToOne
    @JoinColumn(name = "employee_id", foreignKey = @ForeignKey(name = "fk_appointment_employee"))
    private Employee employee;


    // Constructores
    public Appointment() {}

    public Appointment(Long id, LocalDateTime date, Integer duration, String description) {
        this.id = id;
        this.date = date;
        this.duration = duration;
        this.description = description;
    }

    // Métodos
    public Long getId() {
        return id;
    }

    public Appointment setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Appointment setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public Appointment setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Appointment setDescription(String description) {
        this.description = description;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Appointment setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public Appointment setTreatment(Treatment treatment) {
        this.treatment = treatment;
        return this;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Appointment setEmployee(Employee employee) {
        this.employee = employee;
        return this;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", date=" + date +
                ", duration=" + duration +
                '}';
    }
}
