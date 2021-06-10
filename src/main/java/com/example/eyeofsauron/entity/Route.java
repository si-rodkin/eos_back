package com.example.eyeofsauron.entity;

import javax.persistence.*;

@Entity
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "routes_id_seq")
    @SequenceGenerator(name = "routes_id_seq", sequenceName = "routes_id_seq", allocationSize = 1)
    Long id;

    String name;

    @ManyToOne
    SecuredFacility securedFacility;

    public Route(){}
    public Route(Long id, String name, SecuredFacility securedFacility) {
        this.id = id;
        this.name = name;
        this.securedFacility = securedFacility;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public SecuredFacility getSecuredFacility() { return securedFacility; }
    public void setSecuredFacility(SecuredFacility securedFacility) { this.securedFacility = securedFacility; }
}
