package com.demo.springgraphql2.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="yc_target", schema = "public")
@Data
public class Target {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double lat;

    @Column
    private Double lng;

//    @Column(name = "geo_point")
//    @JsonIgnore
//    private Point geoPoint;

    @Column
    private String category;

    @Column(name = "dup_key")
    private Long dupKey;

}
