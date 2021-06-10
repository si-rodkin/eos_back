package com.example.eyeofsauron.entity;

import javax.persistence.*;

@Entity
@Table(name = "marker_reader")
public class MarkerReader {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "marker_reader_id_seq")
    @SequenceGenerator(name = "marker_reader_id_seq", sequenceName = "marker_reader_id_seq", allocationSize = 1)
    Long id;

    String imei;
    String phoneNumber;

    public MarkerReader() { }
    public MarkerReader(Long id, String imei, String phoneNumber) {
        this.id = id;
        this.imei = imei;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getImei() { return imei; }
    public void setImei(String imei) { this.imei = imei; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
