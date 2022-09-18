package com.fortythreesunsets.hairsalon.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "treatments")
public class Treatment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private int estimatedDuration; // en minutos

    // Asociaciones

    // Constructores
    public Treatment() {}

    public Treatment(Long id, String name, Double price, int estimatedDuration) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.estimatedDuration = estimatedDuration;
    }

    // Métodos

    public Long getId() {
        return id;
    }

    public Treatment setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Treatment setName(String name) {
        this.name = name;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Treatment setPrice(Double price) {
        this.price = price;
        return this;
    }

    public int getEstimatedDuration() {
        return estimatedDuration;
    }

    public Treatment setEstimatedDuration(int estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
        return this;
    }

    @Override
    public String toString() {
        return "Treatments{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", estimatedDuration=" + estimatedDuration +
                '}';
    }
}
