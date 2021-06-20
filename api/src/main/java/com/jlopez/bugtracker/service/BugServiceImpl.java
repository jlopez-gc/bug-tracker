package com.jlopez.bugtracker.service;

import com.jlopez.bugtracker.entity.Bug;
import com.jlopez.bugtracker.model.BugPayload;
import com.jlopez.bugtracker.model.BugCreationRequestPayload;
import com.jlopez.bugtracker.model.BugUpdateRequestPayload;
import com.jlopez.bugtracker.repository.BugRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class BugServiceImpl implements BugService {

    private final BugRepository bugRepository;
    private final ConversionService conversionService;

    @Override
    @Transactional()
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

    @Override
    public Optional<BugPayload> create(BugCreationRequestPayload bugCreationRequestPayload) {
        log.info("BugService@create -> Creating bug with with the following information {}", bugCreationRequestPayload);
        Bug newBug = conversionService.convert(bugCreationRequestPayload, Bug.class);
        return Optional.ofNullable(conversionService.convert(bugRepository.save(newBug), BugPayload.class));
    }

    @Override
    public Optional<BugPayload> update(Long id, BugUpdateRequestPayload bugUpdateRequestPayload) {
        log.info("BugService@update -> Updating bug with id={} with the following information {}", id, bugUpdateRequestPayload);
        Optional<Bug> maybeDatabaseBug = bugRepository.findById(id);

        return maybeDatabaseBug.flatMap(databaseBug -> {
            log.info("BugService@update -> Bug with id={} exists in the database", databaseBug.getId());
            Bug modifiedBug = conversionService.convert(bugUpdateRequestPayload, Bug.class);
            modifiedBug.setId(databaseBug.getId());
            modifiedBug.setCreatedAt(databaseBug.getCreatedAt());
            return Optional.ofNullable(conversionService.convert(bugRepository.save(modifiedBug), BugPayload.class));
        });
    }
}
