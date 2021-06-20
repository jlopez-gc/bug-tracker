package com.jlopez.bugtracker.converter;

import com.jlopez.bugtracker.entity.Bug;
import com.jlopez.bugtracker.entity.Status;
import com.jlopez.bugtracker.model.BugCreationRequestPayload;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class BugCreationRequestPayloadToBugConverter extends AbstractRegisterConverter<BugCreationRequestPayload, Bug> {

    public BugCreationRequestPayloadToBugConverter(ConversionService conversionService) {
        super(conversionService);
    }

    @Override
    public Bug convert(BugCreationRequestPayload source) {
        Bug newBug = new Bug();
        newBug.setName(source.getName());
        newBug.setDescription(source.getDescription());
        newBug.setStatus(convert(source.getStatus(), Status.class));
        return newBug;
    }
}
