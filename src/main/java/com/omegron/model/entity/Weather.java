package com.omegron.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "weather")
public class Weather extends BaseEntity {

    private String address;
    private String description;
    private double latitude;
    private double longitude;
    private int queryCost;
    private String resolvedAddress;
    private String timezone;
    private double tzoffset;

    @OneToMany(mappedBy = "weather", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Day> days = new ArrayList<>();

    // Getters and setters

    public String getAddress() {
        return address;
    }

    public Weather setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Weather setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getLatitude() {
        return latitude;
    }

    public Weather setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public double getLongitude() {
        return longitude;
    }

    public Weather setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public int getQueryCost() {
        return queryCost;
    }

    public Weather setQueryCost(int queryCost) {
        this.queryCost = queryCost;
        return this;
    }

    public String getResolvedAddress() {
        return resolvedAddress;
    }

    public Weather setResolvedAddress(String resolvedAddress) {
        this.resolvedAddress = resolvedAddress;
        return this;
    }

    public String getTimezone() {
        return timezone;
    }

    public Weather setTimezone(String timezone) {
        this.timezone = timezone;
        return this;
    }

    public double getTzoffset() {
        return tzoffset;
    }

    public Weather setTzoffset(double tzoffset) {
        this.tzoffset = tzoffset;
        return this;
    }

    public List<Day> getDays() {
        return days;
    }

    public Weather setDays(List<Day> days) {
        this.days = days;
        return this;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", queryCost=" + queryCost +
                ", resolvedAddress='" + resolvedAddress + '\'' +
                ", timezone='" + timezone + '\'' +
                ", tzoffset=" + tzoffset +
                ", days=" + days +
                '}';
    }
}