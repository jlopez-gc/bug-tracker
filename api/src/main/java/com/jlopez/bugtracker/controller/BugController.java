package com.jlopez.bugtracker.controller;

import com.jlopez.bugtracker.model.BugPayload;
import com.jlopez.bugtracker.service.BugService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bug")
@AllArgsConstructor
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
}
