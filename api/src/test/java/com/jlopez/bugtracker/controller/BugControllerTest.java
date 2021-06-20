package com.jlopez.bugtracker.controller;

import com.jlopez.bugtracker.WebMvcTestConfiguration;
import com.jlopez.bugtracker.model.BugPayload;
import com.jlopez.bugtracker.model.BugUpdateRequestPayload;
import com.jlopez.bugtracker.model.StatusPayload;
import com.jlopez.bugtracker.service.BugService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BugController.class)
public class BugControllerTest extends WebMvcTestConfiguration {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BugService bugService;

    @Test
    public void contextLoads() {
        assertThat(bugService).isNotNull();
    }

    @Test
    public void it_returns_ok_when_findAll_is_successful() throws Exception {
        when(bugService.findAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/bug")).andExpect(status().isOk());
        verify(bugService, times(1)).findAll();
    }

    @Test
    public void it_returns_ok_when_findById_is_successful() throws Exception {
        BugPayload bugPayload = BugPayload.builder().id(1L).name("Test").description("Test").createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).build();
        when(bugService.findById(anyLong())).thenReturn(Optional.of(bugPayload));

        mockMvc.perform(get("/bug/1")).andExpect(status().isOk());

        verify(bugService, times(1)).findById(anyLong());
    }

    @Test
    public void it_returns_not_found_when_findById_fails() throws Exception {
        when(bugService.findById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/bug/1")).andExpect(status().isNotFound());

        verify(bugService, times(1)).findById(anyLong());
    }

    @Test
    public void it_returns_ok_when_updating_an_existing_bug() throws Exception {
        when(bugService.update(anyLong(), any())).thenReturn(Optional.of(BugPayload.builder().build()));

        BugUpdateRequestPayload updatePayload = BugUpdateRequestPayload.builder().id(1L).name("Test update").description("Test Update").status(StatusPayload.builder().id(1L).name("Pending").build()).build();

        mockMvc.perform(put("/bug/1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(updatePayload))).andExpect(status().isOk());

        verify(bugService, times(1)).update(anyLong(), any());
    }

    @Test
    public void it_returns_not_found_when_updating_a_non_existing_bug() throws Exception {
        when(bugService.update(anyLong(), any())).thenReturn(Optional.empty());

        BugUpdateRequestPayload updatePayload = BugUpdateRequestPayload.builder().id(200L).name("Test update").description("Test Update").status(StatusPayload.builder().id(1L).name("Pending").build()).build();

        mockMvc.perform(put("/bug/200").contentType(MediaType.APPLICATION_JSON).content(asJsonString(updatePayload))).andExpect(status().isNotFound());

        verify(bugService, times(1)).update(anyLong(), any());
    }

    @Test
    public void it_returns_bad_request_when_updating_with_no_name() throws Exception {
        BugUpdateRequestPayload updatePayload = BugUpdateRequestPayload.builder().id(1L).description("Test Update").status(StatusPayload.builder().id(1L).name("Pending").build()).build();

        mockMvc.perform(put("/bug/1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(updatePayload)))
                .andExpect(status().isBadRequest());

        verify(bugService, times(0)).update(anyLong(), any());
    }

    @Test
    public void it_returns_bad_request_when_updating_with_no_status() throws Exception {
        BugUpdateRequestPayload updatePayload = BugUpdateRequestPayload.builder()
                .id(1L)
                .name("Test")
                .description("Test Update")
                .build();

        mockMvc.perform(put("/bug/1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(updatePayload)))
                .andExpect(status().isBadRequest());

        verify(bugService, times(0)).update(anyLong(), any());
    }

    @Test
    public void it_returns_ok_when_new_bug_is_created() throws Exception {
        when(bugService.create(any())).thenReturn(Optional.of(BugPayload.builder().build()));

        BugUpdateRequestPayload updatePayload = BugUpdateRequestPayload.builder()
                .id(1L)
                .name("Test update")
                .description("Test Update")
                .status(StatusPayload.builder().id(525L).name("Pending").build())
                .build();

        mockMvc.perform(post("/bug").contentType(MediaType.APPLICATION_JSON).content(asJsonString(updatePayload))).andExpect(status().isOk());

        verify(bugService, times(1)).create(any());
    }
}
