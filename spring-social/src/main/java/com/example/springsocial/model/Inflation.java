package com.example.springsocial.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "inflation")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Inflation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "month_name", nullable = false)
    private String month;
    @Column(name = "value", nullable = false)
    private String value;

}
