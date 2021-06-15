package com.jlopez.bugtracker.service;

import com.jlopez.bugtracker.model.BugPayload;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BugServiceTest {

    @Autowired
    private BugService bugService;

    @Test
    public void it_finds_all_bugs() {
        List<BugPayload> all = bugService.findAll();
        Assertions.assertEquals(1, all.size());
    }
}
