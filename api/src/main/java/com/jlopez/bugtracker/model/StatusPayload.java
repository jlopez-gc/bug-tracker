package com.jlopez.bugtracker.model;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StatusPayload implements Serializable {

  private static final long serialVersionUID = 1217859118955602191L;

  private final Long id;

  private final String name;
}
