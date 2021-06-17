package com.jlopez.bugtracker.converter;

import com.jlopez.bugtracker.entity.Bug;
import com.jlopez.bugtracker.model.BugPayload;
import com.jlopez.bugtracker.model.StatusPayload;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class BugToBugPayloadConverter extends AbstractRegisterConverter<Bug, BugPayload> {

    public BugToBugPayloadConverter(ConversionService conversionService) {
        super(conversionService);
    }

    @Override
    public BugPayload convert(Bug source) {
        return BugPayload.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .status(convert(source.getStatus(), StatusPayload.class))
                .createdAt(source.getCreatedAt())
                .updatedAt(source.getUpdatedAt())
                .build();
    }
}
