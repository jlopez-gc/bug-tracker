package com.jlopez.bugtracker.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "bugs")
public class Bug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;
}
