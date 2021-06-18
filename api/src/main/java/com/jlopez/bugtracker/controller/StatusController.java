package com.jlopez.bugtracker.controller;

import com.jlopez.bugtracker.model.StatusPayload;
import com.jlopez.bugtracker.service.StatusService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/status")
@AllArgsConstructor
public class StatusController {

    private final StatusService statusService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StatusPayload> findAll() {
        return statusService.findAll();
    }
}
