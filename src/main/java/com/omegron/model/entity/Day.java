package com.omegron.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "days")
public class Day extends BaseEntity {

    private String datetime;
    private long datetimeEpoch;
    private double tempmax;
    private double tempmin;
    private double temp;
    private double feelslike;
    private double dew;
    private double humidity;
    private double precip;
    private double precipprob;
    private double precipcover;
    private String preciptype;
    private double snow;
    private double snowdepth;
    private double windgust;
    private double windspeed;
    private double winddir;
    private double pressure;
    private double cloudcover;
    private double visibility;
    private double solarradiation;
    private double solarenergy;
    private double uvindex;
    private double moonphase;
    private double severerisk;
    private String sunset;
    private String sunrise;
    private String description;
    private String conditions;
    private String icon;
    private String source;

    @ManyToOne
    @JoinColumn(name = "weather_id")
    private Weather weather;

    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Hour> hours = new ArrayList<>();

    // Getters and setters


    public double getMoonphase() {
        return moonphase;
    }

    public Day setMoonphase(double moonphase) {
        this.moonphase = moonphase;
        return this;
    }

    public String getSunset() {
        return sunset;
    }

    public Day setSunset(String sunset) {
        this.sunset = sunset;
        return this;
    }

    public String getSunrise() {
        return sunrise;
    }

    public Day setSunrise(String sunrise) {
        this.sunrise = sunrise;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Day setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDatetime() {
        return datetime;
    }

    public Day setDatetime(String datetime) {
        this.datetime = datetime;
        return this;
    }

    public long getDatetimeEpoch() {
        return datetimeEpoch;
    }

    public Day setDatetimeEpoch(long datetimeEpoch) {
        this.datetimeEpoch = datetimeEpoch;
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

    public double getFeelslike() {
        return feelslike;
    }

    public Day setFeelslike(double feelslike) {
        this.feelslike = feelslike;
        return this;
    }

    public double getDew() {
        return dew;
    }

    public Day setDew(double dew) {
        this.dew = dew;
        return this;
    }

    public double getHumidity() {
        return humidity;
    }

    public Day setHumidity(double humidity) {
        this.humidity = humidity;
        return this;
    }

    public double getPrecip() {
        return precip;
    }

    public Day setPrecip(double precip) {
        this.precip = precip;
        return this;
    }

    public double getPrecipprob() {
        return precipprob;
    }

    public Day setPrecipprob(double precipprob) {
        this.precipprob = precipprob;
        return this;
    }

    public double getPrecipcover() {
        return precipcover;
    }

    public Day setPrecipcover(double precipcover) {
        this.precipcover = precipcover;
        return this;
    }

    public String getPreciptype() {
        return preciptype;
    }

    public Day setPreciptype(String preciptype) {
        this.preciptype = preciptype;
        return this;
    }

    public double getSnow() {
        return snow;
    }

    public Day setSnow(double snow) {
        this.snow = snow;
        return this;
    }

    public double getSnowdepth() {
        return snowdepth;
    }

    public Day setSnowdepth(double snowdepth) {
        this.snowdepth = snowdepth;
        return this;
    }

    public double getWindgust() {
        return windgust;
    }

    public Day setWindgust(double windgust) {
        this.windgust = windgust;
        return this;
    }

    public double getWindspeed() {
        return windspeed;
    }

    public Day setWindspeed(double windspeed) {
        this.windspeed = windspeed;
        return this;
    }

    public double getWinddir() {
        return winddir;
    }

    public Day setWinddir(double winddir) {
        this.winddir = winddir;
        return this;
    }

    public double getPressure() {
        return pressure;
    }

    public Day setPressure(double pressure) {
        this.pressure = pressure;
        return this;
    }

    public double getCloudcover() {
        return cloudcover;
    }

    public Day setCloudcover(double cloudcover) {
        this.cloudcover = cloudcover;
        return this;
    }

    public double getVisibility() {
        return visibility;
    }

    public Day setVisibility(double visibility) {
        this.visibility = visibility;
        return this;
    }

    public double getSolarradiation() {
        return solarradiation;
    }

    public Day setSolarradiation(double solarradiation) {
        this.solarradiation = solarradiation;
        return this;
    }

    public double getSolarenergy() {
        return solarenergy;
    }

    public Day setSolarenergy(double solarenergy) {
        this.solarenergy = solarenergy;
        return this;
    }

    public double getUvindex() {
        return uvindex;
    }

    public Day setUvindex(double uvindex) {
        this.uvindex = uvindex;
        return this;
    }

    public double getSevererisk() {
        return severerisk;
    }

    public Day setSevererisk(double severerisk) {
        this.severerisk = severerisk;
        return this;
    }

    public String getConditions() {
        return conditions;
    }

    public Day setConditions(String conditions) {
        this.conditions = conditions;
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

    public Weather getWeather() {
        return weather;
    }

    public Day setWeather(Weather weather) {
        this.weather = weather;
        return this;
    }

    public List<Hour> getHours() {
        return hours;
    }

    public Day setHours(List<Hour> hours) {
        this.hours = hours;
        return this;
    }

    @Override
    public String toString() {
        return "Day{" +
                "datetime='" + datetime + '\'' +
                ", datetimeEpoch=" + datetimeEpoch +
                ", tempmax=" + tempmax +
                ", tempmin=" + tempmin +
                ", temp=" + temp +
                ", feelslike=" + feelslike +
                ", dew=" + dew +
                ", humidity=" + humidity +
                ", precip=" + precip +
                ", precipprob=" + precipprob +
                ", precipcover=" + precipcover +
                ", preciptype='" + preciptype + '\'' +
                ", snow=" + snow +
                ", snowdepth=" + snowdepth +
                ", windgust=" + windgust +
                ", windspeed=" + windspeed +
                ", winddir=" + winddir +
                ", pressure=" + pressure +
                ", cloudcover=" + cloudcover +
                ", visibility=" + visibility +
                ", solarradiation=" + solarradiation +
                ", solarenergy=" + solarenergy +
                ", uvindex=" + uvindex +
                ", moonphase=" + moonphase +
                ", severerisk=" + severerisk +
                ", sunset='" + sunset + '\'' +
                ", sunrise='" + sunrise + '\'' +
                ", description='" + description + '\'' +
                ", conditions='" + conditions + '\'' +
                ", icon='" + icon + '\'' +
                ", source='" + source + '\'' +
                ", weather=" + weather +
                ", hours=" + hours +
                '}';
    }
}