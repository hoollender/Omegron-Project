package com.omegron.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "hours")
public class Hour extends BaseEntity {

    private String datetime;
    private long datetimeEpoch;
    private double temp;
    private double feelslike;
    private double humidity;
    private double dew;
    private double precip;
    private double precipprob;
    private String preciptype;
    private double snow;
    private double snowdepth;
    private double windgust;
    private double windspeed;
    private double winddir;
    private double pressure;
    private double visibility;
    private double cloudcover;
    private double solarradiation;
    private double solarenergy;
    private double uvindex;
    private double severerisk;
    private String conditions;
    private String icon;
    private String source;

    @ManyToOne
    @JoinColumn(name = "day_id")
    private Day day;

    // Getters and setters

    public String getDatetime() {
        return datetime;
    }

    public Hour setDatetime(String datetime) {
        this.datetime = datetime;
        return this;
    }

    public long getDatetimeEpoch() {
        return datetimeEpoch;
    }

    public Hour setDatetimeEpoch(long datetimeEpoch) {
        this.datetimeEpoch = datetimeEpoch;
        return this;
    }

    public double getTemp() {
        return temp;
    }

    public Hour setTemp(double temp) {
        this.temp = temp;
        return this;
    }

    public double getFeelslike() {
        return feelslike;
    }

    public Hour setFeelslike(double feelslike) {
        this.feelslike = feelslike;
        return this;
    }

    public double getHumidity() {
        return humidity;
    }

    public Hour setHumidity(double humidity) {
        this.humidity = humidity;
        return this;
    }

    public double getDew() {
        return dew;
    }

    public Hour setDew(double dew) {
        this.dew = dew;
        return this;
    }

    public double getPrecip() {
        return precip;
    }

    public Hour setPrecip(double precip) {
        this.precip = precip;
        return this;
    }

    public double getPrecipprob() {
        return precipprob;
    }

    public Hour setPrecipprob(double precipprob) {
        this.precipprob = precipprob;
        return this;
    }

    public String getPreciptype() {
        return preciptype;
    }

    public Hour setPreciptype(String preciptype) {
        this.preciptype = preciptype;
        return this;
    }

    public double getSnow() {
        return snow;
    }

    public Hour setSnow(double snow) {
        this.snow = snow;
        return this;
    }

    public double getSnowdepth() {
        return snowdepth;
    }

    public Hour setSnowdepth(double snowdepth) {
        this.snowdepth = snowdepth;
        return this;
    }

    public double getWindgust() {
        return windgust;
    }

    public Hour setWindgust(double windgust) {
        this.windgust = windgust;
        return this;
    }

    public double getWindspeed() {
        return windspeed;
    }

    public Hour setWindspeed(double windspeed) {
        this.windspeed = windspeed;
        return this;
    }

    public double getWinddir() {
        return winddir;
    }

    public Hour setWinddir(double winddir) {
        this.winddir = winddir;
        return this;
    }

    public double getPressure() {
        return pressure;
    }

    public Hour setPressure(double pressure) {
        this.pressure = pressure;
        return this;
    }

    public double getVisibility() {
        return visibility;
    }

    public Hour setVisibility(double visibility) {
        this.visibility = visibility;
        return this;
    }

    public double getCloudcover() {
        return cloudcover;
    }

    public Hour setCloudcover(double cloudcover) {
        this.cloudcover = cloudcover;
        return this;
    }

    public double getSolarradiation() {
        return solarradiation;
    }

    public Hour setSolarradiation(double solarradiation) {
        this.solarradiation = solarradiation;
        return this;
    }

    public double getSolarenergy() {
        return solarenergy;
    }

    public Hour setSolarenergy(double solarenergy) {
        this.solarenergy = solarenergy;
        return this;
    }

    public double getUvindex() {
        return uvindex;
    }

    public Hour setUvindex(double uvindex) {
        this.uvindex = uvindex;
        return this;
    }

    public double getSevererisk() {
        return severerisk;
    }

    public Hour setSevererisk(double severerisk) {
        this.severerisk = severerisk;
        return this;
    }

    public String getConditions() {
        return conditions;
    }

    public Hour setConditions(String conditions) {
        this.conditions = conditions;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public Hour setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public String getSource() {
        return source;
    }

    public Hour setSource(String source) {
        this.source = source;
        return this;
    }

    public Day getDay() {
        return day;
    }

    public Hour setDay(Day day) {
        this.day = day;
        return this;
    }

    @Override
    public String toString() {
        return "Hour{" +
                "datetime='" + datetime + '\'' +
                ", datetimeEpoch=" + datetimeEpoch +
                ", temp=" + temp +
                ", feelslike=" + feelslike +
                ", humidity=" + humidity +
                ", dew=" + dew +
                ", precip=" + precip +
                ", precipprob=" + precipprob +
                ", preciptype='" + preciptype + '\'' +
                ", snow=" + snow +
                ", snowdepth=" + snowdepth +
                ", windgust=" + windgust +
                ", windspeed=" + windspeed +
                ", winddir=" + winddir +
                ", pressure=" + pressure +
                ", visibility=" + visibility +
                ", cloudcover=" + cloudcover +
                ", solarradiation=" + solarradiation +
                ", solarenergy=" + solarenergy +
                ", uvindex=" + uvindex +
                ", severerisk=" + severerisk +
                ", conditions='" + conditions + '\'' +
                ", icon='" + icon + '\'' +
                ", source='" + source + '\'' +
                ", day=" + day +
                '}';
    }
}