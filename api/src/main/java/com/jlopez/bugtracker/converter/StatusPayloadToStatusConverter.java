package com.jlopez.bugtracker.converter;

import com.jlopez.bugtracker.entity.Status;
import com.jlopez.bugtracker.model.StatusPayload;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StatusPayloadToStatusConverter implements Converter<StatusPayload, Status> {
    @Override
    public Status convert(StatusPayload source) {
        Status newStatus = new Status();
        newStatus.setId(source.getId());
        newStatus.setName(source.getName());
        return newStatus;
    }
}
