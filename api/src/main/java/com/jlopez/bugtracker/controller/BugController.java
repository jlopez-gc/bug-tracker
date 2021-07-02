package com.jlopez.bugtracker.controller;

import com.jlopez.bugtracker.model.BugCreationRequestPayload;
import com.jlopez.bugtracker.model.BugPayload;
import com.jlopez.bugtracker.model.BugUpdateRequestPayload;
import com.jlopez.bugtracker.service.BugService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/bug")
public class BugController {

  private final BugService bugService;

  @GetMapping
  public ResponseEntity<List<BugPayload>> findAll() {
    return ResponseEntity.ok(bugService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<BugPayload> findById(@PathVariable Long id) {
    return bugService.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<BugPayload> create(@RequestBody @Valid BugCreationRequestPayload bugCreationRequestPayload) {
    BugPayload newBug = bugService.create(bugCreationRequestPayload);
    return ResponseEntity.created(ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(newBug.getId())
        .toUri()).body(newBug);
  }

  @PutMapping("/{id}")
  public ResponseEntity<BugPayload> update(@PathVariable Long id, @RequestBody @Valid BugUpdateRequestPayload bugUpdateRequestPayload) {
    if (!bugUpdateRequestPayload.getId().equals(id)) {
      throw new BugUpdateRequestMismatchIdException("Resource id mismatch payload id");
    }

    return bugService.update(bugUpdateRequestPayload)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @ResponseStatus(HttpStatus.CONFLICT)
  public static class BugUpdateRequestMismatchIdException extends RuntimeException {

    public BugUpdateRequestMismatchIdException(String message) {
      super(message);
    }
  }
}
