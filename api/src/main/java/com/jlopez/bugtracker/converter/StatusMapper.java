package com.jlopez.bugtracker.converter;

import com.jlopez.bugtracker.entity.Status;
import com.jlopez.bugtracker.model.StatusPayload;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface StatusMapper {

  StatusPayload statusToStatusPayload(Status source);

  List<StatusPayload> statusesToStatusPayloads(List<Status> sources);

}
