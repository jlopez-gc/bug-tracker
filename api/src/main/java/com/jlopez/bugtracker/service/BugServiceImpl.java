package com.jlopez.bugtracker.service;

import com.jlopez.bugtracker.converter.BugMapper;
import com.jlopez.bugtracker.entity.Bug;
import com.jlopez.bugtracker.model.BugCreationRequestPayload;
import com.jlopez.bugtracker.model.BugPayload;
import com.jlopez.bugtracker.model.BugUpdateRequestPayload;
import com.jlopez.bugtracker.repository.BugRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class BugServiceImpl implements BugService {

  private final BugRepository bugRepository;

  private final BugMapper bugMapper;

  @Override
  @Transactional()
  public List<BugPayload> findAll() {
    return bugMapper.bugsToBugPayloads(bugRepository.findAll());
  }

  @Override
  public Optional<BugPayload> findById(Long id) {
    return bugRepository
        .findById(id)
        .map(bugMapper::bugToBugPayload);
  }

  @Override
  public BugPayload create(BugCreationRequestPayload bugCreationRequestPayload) {
    log.info("BugService@create -> Creating bug with with the following information {}", bugCreationRequestPayload);
    Bug newBug = bugMapper.bugCreationRequestPayloadToBug(bugCreationRequestPayload);
    return bugMapper.bugToBugPayload(bugRepository.save(newBug));
  }

  @Override
  public Optional<BugPayload> update(BugUpdateRequestPayload bugUpdateRequestPayload) {
    log.info("BugService@update -> Updating bug with the following information {}", bugUpdateRequestPayload);
    return bugRepository.findById(bugUpdateRequestPayload.getId()).map(databaseBug -> {
      log.info("BugService@update -> Bug with id={} exists in the database", databaseBug.getId());
      Bug modifiedBug = bugMapper.bugUpdateRequestPayloadToBug(bugUpdateRequestPayload);
      databaseBug.setDescription(modifiedBug.getDescription());
      databaseBug.setName(modifiedBug.getName());
      databaseBug.setStatus(modifiedBug.getStatus());
      return bugMapper.bugToBugPayload(bugRepository.save(modifiedBug));
    });
  }
}
