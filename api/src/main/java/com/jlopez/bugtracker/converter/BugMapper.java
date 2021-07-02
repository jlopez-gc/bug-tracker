package com.jlopez.bugtracker.converter;

import com.jlopez.bugtracker.entity.Bug;
import com.jlopez.bugtracker.model.BugCreationRequestPayload;
import com.jlopez.bugtracker.model.BugPayload;
import com.jlopez.bugtracker.model.BugUpdateRequestPayload;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface BugMapper {

  @Mapping(ignore = true, target = "id")
  @Mapping(ignore = true, target = "createdAt")
  @Mapping(ignore = true, target = "updatedAt")
  Bug bugCreationRequestPayloadToBug(BugCreationRequestPayload source);

  @Mapping(ignore = true, target = "createdAt")
  @Mapping(ignore = true, target = "updatedAt")
  Bug bugUpdateRequestPayloadToBug(BugUpdateRequestPayload bugUpdateRequestPayload);

  BugPayload bugToBugPayload(Bug source);

  List<BugPayload> bugsToBugPayloads(List<Bug> sources);
}
