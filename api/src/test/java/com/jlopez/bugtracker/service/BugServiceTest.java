package com.jlopez.bugtracker.service;

import com.jlopez.bugtracker.exception.ResourceNotFoundException;
import com.jlopez.bugtracker.model.BugPayload;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class BugServiceTest {

    @Autowired
    private BugService bugService;

    @Test
    public void it_finds_all_bugs() {
        List<BugPayload> allBugs = bugService.findAll();
        Assertions.assertEquals(2, allBugs.size());
    }

    @Test
    public void it_finds_a_bug_by_id() {
        Optional<BugPayload> maybeBug = bugService.findById(1L);
        Assertions.assertTrue(maybeBug.isPresent());
    }

    @Test
    public void it_returns_an_empty_optional_if_bug_not_found() {
        Optional<BugPayload> maybeBug = bugService.findById(200L);
        Assertions.assertTrue(maybeBug.isEmpty());
    }
        Exception exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            bugService.findById(200L);
        });

        Assertions.assertEquals("Bug with id 200 not found", exception.getMessage());
    }
}
