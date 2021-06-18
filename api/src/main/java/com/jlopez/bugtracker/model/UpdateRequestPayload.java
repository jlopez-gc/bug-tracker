package com.jlopez.bugtracker.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class UpdateRequestPayload implements Serializable {

    private static final long serialVersionUID = -6101127785318552413L;

    @NotNull
    private Long id;

    @Size(max = 255, message = "The title cannot be longer than 255 characters")
    private String name;

    private String description;

    @NotNull
    private StatusPayload status;
}
