package com.fortythreesunsets.hairsalon.dto;

public class EarningsDTO {

    private Double earnings;
    private Integer year;
    private Integer month;
    private Integer day;

    public EarningsDTO() {}

    public EarningsDTO(Double earnings) {
        this.earnings = earnings;
    }

    public Double getEarnings() {
        return earnings;
    }

    public EarningsDTO setEarnings(Double earnings) {
        this.earnings = earnings;
        return this;
    }
}
