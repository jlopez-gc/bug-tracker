package com.jlopez.bugtracker.service;

import com.jlopez.bugtracker.model.BugPayload;
import com.jlopez.bugtracker.model.UpdateRequestPayload;

import java.util.List;
import java.util.Optional;

public interface BugService {
    List<BugPayload> findAll();
    Optional<BugPayload> findById(Long id);
    void update(Long id, UpdateRequestPayload updateRequestPayload);
}
