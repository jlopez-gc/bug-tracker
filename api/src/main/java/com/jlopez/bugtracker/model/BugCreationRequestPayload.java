package com.jlopez.bugtracker.model;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BugCreationRequestPayload implements Serializable {

  private static final long serialVersionUID = 5873297484454588086L;

  @Size(max = 255, message = "The title cannot be longer than 255 characters")
  private final String name;

  private final String description;

  @NotNull
  private final StatusPayload status;
}
