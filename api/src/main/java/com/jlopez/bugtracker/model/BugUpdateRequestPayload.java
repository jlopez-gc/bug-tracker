package com.jlopez.bugtracker.model;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BugUpdateRequestPayload implements Serializable {

  private static final long serialVersionUID = 8143115377640633193L;

  @NotNull
  private final Long id;

  @NotNull
  @Size(max = 255, message = "The title cannot be longer than 255 characters")
  private final String name;

  private final String description;

  @NotNull
  private final StatusPayload status;
}
