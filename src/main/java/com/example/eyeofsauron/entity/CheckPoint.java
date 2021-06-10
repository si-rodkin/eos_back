package com.example.eyeofsauron.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name = "check_points")
public class CheckPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "check_points_id_seq")
    @SequenceGenerator(name = "check_points_id_seq", sequenceName = "check_points_id_seq", allocationSize = 1)
    private Long id;

    String name;
    LocalDateTime readTime;
    LocalDateTime allowanceTime;
    Date bypassDay;

    @OneToOne
    Marker marker;
    @OneToOne
    MarkerReader markerReader;
    @ManyToOne
    Route route;

    public CheckPoint(){}
    public CheckPoint(Long id, String name, LocalDateTime readTime, LocalDateTime allowanceTime, Date bypassDay, Marker marker, MarkerReader markerReader, Route route) {
        this.id = id;
        this.name = name;
        this.readTime = readTime;
        this.allowanceTime = allowanceTime;
        this.bypassDay = bypassDay;
        this.marker = marker;
        this.markerReader = markerReader;
        this.route = route;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDateTime getReadTime() { return readTime; }
    public void setReadTime(LocalDateTime readTime) { this.readTime = readTime; }

    public LocalDateTime getAllowanceTime() { return allowanceTime; }
    public void setAllowanceTime(LocalDateTime allowanceTime) { this.allowanceTime = allowanceTime; }

    public Date getBypassDay() { return bypassDay; }
    public void setBypassDay(Date bypassDay) { this.bypassDay = bypassDay; }

    public Marker getMarker() { return marker; }
    public void setMarker(Marker marker) { this.marker = marker; }

    public MarkerReader getMarkerReader() { return markerReader; }
    public void setMarkerReader(MarkerReader markerReader) { this.markerReader = markerReader; }

    public Route getRoute() { return route; }
    public void setRoute(Route route) { this.route = route; }
}
