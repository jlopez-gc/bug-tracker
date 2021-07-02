package com.jlopez.bugtracker.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Status implements Serializable {

  private static final long serialVersionUID = -6167894638199394361L;

  @Id
  private Long id;

  private String name;
}
