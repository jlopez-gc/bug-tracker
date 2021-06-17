package com.jlopez.bugtracker.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class StatusPayload implements Serializable {

    private static final long serialVersionUID = -3773559041735846973L;

    private Long id;

    private String name;
}
