package com.omegron.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "days")
public class Day extends BaseEntity {
    private String datetime;
    private double tempmax;
    private double tempmin;
    private double temp;
    private String sunrise;
    private String sunset;
    private String conditions;
    private String description;
    private String icon;
    private String source;

    public String getDatetime() {
        return datetime;
    }

    public Day setDatetime(String datetime) {
        this.datetime = datetime;
        return this;
    }

    public double getTempmax() {
        return tempmax;
    }

    public Day setTempmax(double tempmax) {
        this.tempmax = tempmax;
        return this;
    }

    public double getTempmin() {
        return tempmin;
    }

    public Day setTempmin(double tempmin) {
        this.tempmin = tempmin;
        return this;
    }

    public double getTemp() {
        return temp;
    }

    public Day setTemp(double temp) {
        this.temp = temp;
        return this;
    }

    public String getSunrise() {
        return sunrise;
    }

    public Day setSunrise(String sunrise) {
        this.sunrise = sunrise;
        return this;
    }

    public String getSunset() {
        return sunset;
    }

    public Day setSunset(String sunset) {
        this.sunset = sunset;
        return this;
    }

    public String getConditions() {
        return conditions;
    }

    public Day setConditions(String conditions) {
        this.conditions = conditions;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Day setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public Day setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public String getSource() {
        return source;
    }

    public Day setSource(String source) {
        this.source = source;
        return this;
    }
}