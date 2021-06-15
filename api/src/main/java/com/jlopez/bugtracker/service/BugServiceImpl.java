package com.jlopez.bugtracker.service;

import com.jlopez.bugtracker.model.BugPayload;
import com.jlopez.bugtracker.repository.BugRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BugServiceImpl implements BugService {

    private final BugRepository bugRepository;
    private final ConversionService conversionService;

    @Override
    public List<BugPayload> findAll() {
        return bugRepository
                .findAll()
                .stream()
                .map(bug -> conversionService.convert(bug, BugPayload.class))
                .collect(Collectors.toList());
    }
}
