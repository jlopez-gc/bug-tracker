package com.jlopez.bugtracker.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
public class UpdateRequestPayload implements Serializable {

    private static final long serialVersionUID = -6101127785318552413L;

    @NotNull
    private final Long id;

    @NotNull
    @Size(max = 255, message = "The title cannot be longer than 255 characters")
    private final String name;

    private final String description;

    @NotNull
    private final StatusPayload status;
}
