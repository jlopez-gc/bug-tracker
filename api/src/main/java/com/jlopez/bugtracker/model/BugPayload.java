package com.jlopez.bugtracker.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BugPayload implements Serializable {

  private static final long serialVersionUID = -8865810957982887270L;

  private final Long id;

  private final String name;

  private final String description;

  private final StatusPayload status;

  private final LocalDateTime createdAt;

  private final LocalDateTime updatedAt;
}
