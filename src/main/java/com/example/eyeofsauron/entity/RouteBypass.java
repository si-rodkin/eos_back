package com.example.eyeofsauron.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="route_bypasses")
public class RouteBypass {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "route_bypasses_id_seq")
    @SequenceGenerator(name = "route_bypasses_id_seq", sequenceName = "route_bypasses_id_seq", allocationSize = 1)
    Long id;

    String name;
    LocalDateTime bypassTime;

    @ManyToOne
    Route route;

    public RouteBypass(){}
    public RouteBypass(Long id, String name, LocalDateTime bypassTime, Route route) {
        this.id = id;
        this.name = name;
        this.bypassTime = bypassTime;
        this.route = route;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDateTime getBypassTime() { return bypassTime; }
    public void setBypassTime(LocalDateTime bypassTime) { this.bypassTime = bypassTime; }

    public Route getRoute() { return route; }
    public void setRoute(Route route) { this.route = route; }
}
