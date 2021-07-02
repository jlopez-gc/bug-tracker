package com.jlopez.bugtracker.service;

import com.jlopez.bugtracker.converter.StatusMapper;
import com.jlopez.bugtracker.model.StatusPayload;
import com.jlopez.bugtracker.repository.StatusRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StatusServiceImpl implements StatusService {

  private final StatusRepository statusRepository;

  private final StatusMapper statusMapper;

  @Override
  public List<StatusPayload> findAll() {
    return statusMapper.statusesToStatusPayloads(statusRepository.findAll());
  }
}
