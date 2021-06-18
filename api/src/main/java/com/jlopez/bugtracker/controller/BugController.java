package com.jlopez.bugtracker.controller;

import com.jlopez.bugtracker.model.BugPayload;
import com.jlopez.bugtracker.model.UpdateRequestPayload;
import com.jlopez.bugtracker.service.BugService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public BugPayload findById(@PathVariable Long id) {
        return bugService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable Long id, @RequestBody @Valid UpdateRequestPayload updateRequestPayload) {
        log.info("Updating bug with id={} with the following information {}", id, updateRequestPayload);
        bugService.update(id, updateRequestPayload);
        log.info("Updated bug with id={}", id);
    }
}
