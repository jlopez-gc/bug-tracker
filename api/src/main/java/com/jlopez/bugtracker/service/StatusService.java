package com.jlopez.bugtracker.service;

import com.jlopez.bugtracker.model.StatusPayload;
import java.util.List;

public interface StatusService {

  List<StatusPayload> findAll();
}
