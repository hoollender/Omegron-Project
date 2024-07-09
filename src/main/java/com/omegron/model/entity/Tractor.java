package com.omegron.model.entity;

import com.omegron.model.enums.EngineTypeEnum;
import com.omegron.model.enums.ManufacturersEnum;
import com.omegron.model.enums.TransmissionTypeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tractors")
public class Tractor extends BaseEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ManufacturersEnum manufacturer;
    @Column(nullable = false)
    @Size(min = 2, max = 50)
    private String model;
    @Column(nullable = false)
    private int year;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private int workHours;
    @Column(nullable = false)
    private String imageUrl;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EngineTypeEnum engine;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransmissionTypeEnum transmission;

    public String getDescription() {
        return description;
    }

    public Tractor setDescription(String description) {
        this.description = description;
        return this;
    }

    public EngineTypeEnum getEngine() {
        return engine;
    }

    public Tractor setEngine(EngineTypeEnum engine) {
        this.engine = engine;
        return this;
    }

    public TransmissionTypeEnum getTransmission() {
        return transmission;
    }

    public Tractor setTransmission(TransmissionTypeEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    public ManufacturersEnum getManufacturer() {
        return manufacturer;
    }

    public Tractor setManufacturer(ManufacturersEnum manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Tractor setModel(String model) {
        this.model = model;
        return this;
    }

    public int getYear() {
        return year;
    }

    public Tractor setYear(int year) {
        this.year = year;
        return this;
    }

    public int getWorkHours() {
        return workHours;
    }

    public Tractor setWorkHours(int workHours) {
        this.workHours = workHours;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Tractor setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
