package com.jlopez.bugtracker.service;

import com.jlopez.bugtracker.entity.Bug;
import com.jlopez.bugtracker.entity.Status;
import com.jlopez.bugtracker.exception.InvalidInformationException;
import com.jlopez.bugtracker.exception.ResourceNotFoundException;
import com.jlopez.bugtracker.model.BugPayload;
import com.jlopez.bugtracker.model.UpdateRequestPayload;
import com.jlopez.bugtracker.repository.BugRepository;
import com.jlopez.bugtracker.repository.StatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BugServiceImpl implements BugService {

    private final BugRepository bugRepository;
    private final StatusRepository statusRepository;
    private final ConversionService conversionService;

    @Override
    public List<BugPayload> findAll() {
        return bugRepository
                .findAll()
                .stream()
                .map(bug -> conversionService.convert(bug, BugPayload.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BugPayload> findById(Long id) {
        return bugRepository
                .findById(id)
                .flatMap(bug -> Optional.ofNullable(conversionService.convert(bug, BugPayload.class)));
    }
    }

    @Override
    public void update(Long id, UpdateRequestPayload updateRequestPayload) {
        Bug bug = bugRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Bug with id %s not found", id)));

        Status status = statusRepository
                .findById(updateRequestPayload.getStatus().getId())
                .orElseThrow(() -> new InvalidInformationException(String.format("Bug with id %s could not be updated, wrong information provided", id)));

        bug.setName(updateRequestPayload.getName());
        bug.setDescription(updateRequestPayload.getDescription());
        bug.setStatus(status);

        bugRepository.save(bug);
    }
}
