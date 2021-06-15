package com.jlopez.bugtracker.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class BugPayload implements Serializable {
    private static final long serialVersionUID = -8865810957982887270L;
    private Long id;
    private String name;
    private String description;
}
