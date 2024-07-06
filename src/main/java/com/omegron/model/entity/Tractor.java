package com.omegron.model.entity;

import com.omegron.model.enums.EngineTypeEnum;
import com.omegron.model.enums.ManufacturersEnum;
import com.omegron.model.enums.TransmissionTypeEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "tractors")
public class Tractor extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private ManufacturersEnum manufacturer;

    private String model;

    private int year;

    private String description;

    private int workHours;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private EngineTypeEnum engine;

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
