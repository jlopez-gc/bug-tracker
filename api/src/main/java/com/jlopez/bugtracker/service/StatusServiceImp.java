package com.jlopez.bugtracker.service;

import com.jlopez.bugtracker.model.StatusPayload;
import com.jlopez.bugtracker.repository.StatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StatusServiceImp implements StatusService {

    private final StatusRepository statusRepository;
    private final ConversionService conversionService;

    @Override
    public List<StatusPayload> findAll() {
        return statusRepository
                .findAll()
                .stream()
                .map(status -> conversionService.convert(status, StatusPayload.class))
                .collect(Collectors.toList());
    }
}
