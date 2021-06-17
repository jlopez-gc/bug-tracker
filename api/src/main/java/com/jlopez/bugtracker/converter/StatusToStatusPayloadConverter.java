package com.jlopez.bugtracker.converter;

import com.jlopez.bugtracker.entity.Status;
import com.jlopez.bugtracker.model.StatusPayload;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StatusToStatusPayloadConverter implements Converter<Status, StatusPayload> {
    @Override
    public StatusPayload convert(Status source) {
        return StatusPayload
                .builder()
                .id(source.getId())
                .name(source.getName())
                .build();
    }
}
