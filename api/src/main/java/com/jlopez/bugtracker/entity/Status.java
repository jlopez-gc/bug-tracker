package com.jlopez.bugtracker.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class Status implements Serializable {

    private static final long serialVersionUID = -6167894638199394361L;

    @Id
    private Long id;

    private String name;
}
