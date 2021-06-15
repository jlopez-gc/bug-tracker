package com.jlopez.bugtracker.controller;

import com.jlopez.bugtracker.model.BugPayload;
import com.jlopez.bugtracker.service.BugService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bugs")
@AllArgsConstructor
public class BugController {

    private final BugService bugService;

    @GetMapping
    public List<BugPayload> findAll() {
        return bugService.findAll();
    }
}
