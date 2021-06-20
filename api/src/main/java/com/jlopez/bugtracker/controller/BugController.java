package com.jlopez.bugtracker.controller;

import com.jlopez.bugtracker.model.BugPayload;
import com.jlopez.bugtracker.model.BugUpdateRequestPayload;
import com.jlopez.bugtracker.model.BugCreationRequestPayload;
import com.jlopez.bugtracker.service.BugService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bug")
@AllArgsConstructor
@Slf4j
public class BugController {

    private final BugService bugService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BugPayload> findAll() {
        return bugService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BugPayload> findById(@PathVariable Long id) {
        return bugService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BugPayload> create(@RequestBody @Valid BugCreationRequestPayload bugCreationRequestPayload) {
        return bugService.create(bugCreationRequestPayload).map(ResponseEntity::ok).orElse(ResponseEntity.unprocessableEntity().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BugPayload> update(@PathVariable Long id, @RequestBody @Valid BugUpdateRequestPayload bugUpdateRequestPayload) {
        return bugService.update(id, bugUpdateRequestPayload).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
