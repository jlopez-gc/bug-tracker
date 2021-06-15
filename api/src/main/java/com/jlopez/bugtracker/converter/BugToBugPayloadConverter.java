package com.jlopez.bugtracker.converter;

import com.jlopez.bugtracker.entity.Bug;
import com.jlopez.bugtracker.model.BugPayload;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BugToBugPayloadConverter implements Converter<Bug, BugPayload> {
    @Override
    public BugPayload convert(Bug source) {
        return BugPayload.builder()
                .id(source.getId())
                .name(source.getName())
                .description(source.getDescription())
                .build();
    }
}
