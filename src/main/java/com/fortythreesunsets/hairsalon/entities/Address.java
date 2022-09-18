package com.fortythreesunsets.hairsalon.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "addresses")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    private String postalCode;

    private String state;

    public Address() {}

    public Address(Long id, String street, String postalCode, String state) {
        this.id = id;
        this.street = street;
        this.postalCode = postalCode;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public Address setId(Long id) {
        this.id = id;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public Address setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Address setPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public String getState() {
        return state;
    }

    public Address setState(String state) {
        this.state = state;
        return this;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
