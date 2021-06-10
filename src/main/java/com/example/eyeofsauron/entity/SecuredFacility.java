package com.example.eyeofsauron.entity;

import javax.persistence.*;

@Entity
@Table(name = "secured_facility")
public class SecuredFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "secured_facilities_id_seq")
    @SequenceGenerator(name = "secured_facilities_id_seq", sequenceName = "secured_facilities_id_seq", allocationSize = 1)
    Long id;

    String name;
    String itn;

    public SecuredFacility(){}
    public SecuredFacility(Long id, String name, String itn) {
        this.id = id;
        this.name = name;
        this.itn = itn;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getItn() { return itn; }
    public void setItn(String itn) { this.itn = itn; }
}
