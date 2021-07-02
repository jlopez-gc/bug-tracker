package com.jlopez.bugtracker.service;

import com.jlopez.bugtracker.model.BugCreationRequestPayload;
import com.jlopez.bugtracker.model.BugPayload;
import com.jlopez.bugtracker.model.BugUpdateRequestPayload;
import java.util.List;
import java.util.Optional;

public interface BugService {

  List<BugPayload> findAll();

  Optional<BugPayload> findById(Long id);

  BugPayload create(BugCreationRequestPayload bugCreationRequestPayload);

  Optional<BugPayload> update(BugUpdateRequestPayload bugUpdateRequestPayload);
}
