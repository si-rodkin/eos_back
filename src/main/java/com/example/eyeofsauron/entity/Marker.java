package com.example.eyeofsauron.entity;

import javax.persistence.*;

@Entity
@Table(name = "markers")
public class Marker {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "markers_id_seq")
    @SequenceGenerator(name = "markers_id_seq", sequenceName = "markers_id_seq", allocationSize = 1)
    Long id;

    String name;
    String rfid;

    @OneToOne
    Route route;

    public Marker(){}
    public Marker(Long id, String name, String rfid, Route route) {
        this.id = id;
        this.name = name;
        this.rfid = rfid;
        this.route = route;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRfid() { return rfid; }
    public void setRfid(String rfid) { this.rfid = rfid; }

    public Route getRoute() { return route; }
    public void setRoute(Route route) { this.route = route; }
}
