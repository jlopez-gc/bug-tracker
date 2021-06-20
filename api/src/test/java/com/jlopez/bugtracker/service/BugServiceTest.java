package com.jlopez.bugtracker.service;

import com.jlopez.bugtracker.exception.InvalidInformationException;
import com.jlopez.bugtracker.exception.ResourceNotFoundException;
import com.jlopez.bugtracker.model.BugPayload;
import com.jlopez.bugtracker.model.StatusPayload;
import com.jlopez.bugtracker.model.UpdateRequestPayload;
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
        Assertions.assertFalse(maybeBug.isPresent());
    }

    @Test
    public void it_throws_exception_when_updating_a_non_existent_bug() {
        UpdateRequestPayload updateRequest = UpdateRequestPayload.builder().id(200L).name("Testing Update").description("Updated description").build();

        Exception exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            bugService.update(200L, updateRequest);
        });

        Assertions.assertEquals("Bug with id 200 not found", exception.getMessage());
    }

    @Test
    public void it_throws_exception_when_updating_a_bug_to_unknown_status() {
        StatusPayload nonExistentStatus = StatusPayload.builder().id(500L).name("Does not exists").build();
        UpdateRequestPayload updateRequest = UpdateRequestPayload.builder().id(1L).name("Testing Update").description("Updated description").status(nonExistentStatus).build();

        Exception exception = Assertions.assertThrows(InvalidInformationException.class, () -> {
            bugService.update(1L, updateRequest);
        });

        Assertions.assertEquals("Bug with id 1 could not be updated, wrong information provided", exception.getMessage());
    }

}
