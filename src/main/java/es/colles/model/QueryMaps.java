package es.colles.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class QueryMaps {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 48)
    @Size(max = 48)
    private String description;

    @NotEmpty
    private String origins;

    @NotEmpty
    private String destinations;

    private String key;

    //    Optional
    private String mode;

    private String language;

    //Tolls, highways, ferries,indoor
    private String avoid;

    private String units;

    private String arrivalTime;

    private String departureTime;

    @DateTimeFormat(pattern = "ddMMYYYY")
    private Date timeStamp;

    public String getOrigins() {
        return origins;
    }

    public void setOrigins(String origins) {
        this.origins = origins;
    }

    public String getDestinations() {
        return destinations;
    }

    public void setDestinations(String destinations) {
        this.destinations = destinations;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAvoid() {
        return avoid;
    }

    public void setAvoid(String avoid) {
        this.avoid = avoid;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    @PrePersist
    public void setTimeStamp() {
        Date timeStamp = new Date();
        this.timeStamp = timeStamp;
    }
}
