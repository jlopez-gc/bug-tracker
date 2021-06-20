package com.jlopez.bugtracker.service;

import com.jlopez.bugtracker.model.BugCreationRequestPayload;
import com.jlopez.bugtracker.model.BugPayload;
import com.jlopez.bugtracker.model.BugUpdateRequestPayload;
import com.jlopez.bugtracker.model.StatusPayload;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class BugServiceTest {

    private final int INITIAL_NUMBER_OF_BUGS = 2;

    @Autowired
    private BugService bugService;

    @Test
    public void it_finds_all_bugs() {
        List<BugPayload> allBugs = bugService.findAll();
        Assertions.assertEquals(INITIAL_NUMBER_OF_BUGS, allBugs.size());
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
    public void it_returns_an_empty_optional_when_updating_a_non_existent_bug() {
        BugUpdateRequestPayload updateRequest = BugUpdateRequestPayload.builder().id(200L).name("Testing Update").description("Updated description").build();

        Optional<BugPayload> maybeUpdatedBug = bugService.update(200L, updateRequest);

        Assertions.assertFalse(maybeUpdatedBug.isPresent());
    }

    @Test
    @DirtiesContext
    public void it_returns_an_updated_bug_payload_when_updating_a_non_existent_bug() {
        StatusPayload requestedStatus = StatusPayload.builder().id(1L).name("Pending").build();
        BugUpdateRequestPayload updateRequest = BugUpdateRequestPayload.builder().id(1L).name("Testing Update in Test").description("Updated description").status(requestedStatus).build();

        Optional<BugPayload> maybeUpdatedBug = bugService.update(1L, updateRequest);

        Assertions.assertTrue(maybeUpdatedBug.isPresent());
        Assertions.assertEquals("Testing Update in Test", maybeUpdatedBug.get().getName());
    }

    @Test
    @DirtiesContext
    public void it_returns_a_bug_payload_when_creating_a_new_bug() {
        StatusPayload requestedStatus = StatusPayload.builder().id(1L).name("Pending").build();
        BugCreationRequestPayload creationRequest = BugCreationRequestPayload.builder().name("Testing Create").description("Created description").status(requestedStatus).build();

        Optional<BugPayload> maybeCreatedBug = bugService.create(creationRequest);

        Assertions.assertTrue(maybeCreatedBug.isPresent());
        Assertions.assertEquals(INITIAL_NUMBER_OF_BUGS + 1, bugService.findAll().size());
    }

}
