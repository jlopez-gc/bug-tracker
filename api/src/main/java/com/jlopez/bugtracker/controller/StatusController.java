package com.jlopez.bugtracker.controller;

import com.jlopez.bugtracker.model.StatusPayload;
import com.jlopez.bugtracker.service.StatusService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/status")
public class StatusController {

  private final StatusService statusService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<List<StatusPayload>> findAll() {
    return ResponseEntity.ok(statusService.findAll());
  }
}
