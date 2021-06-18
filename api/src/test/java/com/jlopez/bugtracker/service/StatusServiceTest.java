package com.jlopez.bugtracker.service;

import com.jlopez.bugtracker.model.StatusPayload;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class StatusServiceTest {

    @Autowired
    private StatusService statusService;

    @Test
    public void it_finds_all_statuses() {
        List<StatusPayload> allStatuses = statusService.findAll();
        Assertions.assertEquals(3, allStatuses.size());
    }
}
