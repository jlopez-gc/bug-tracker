package com.jlopez.bugtracker.service;

import com.jlopez.bugtracker.model.BugPayload;

import java.util.List;

public interface BugService {
    List<BugPayload> findAll();
    BugPayload findById(Long id);
}
