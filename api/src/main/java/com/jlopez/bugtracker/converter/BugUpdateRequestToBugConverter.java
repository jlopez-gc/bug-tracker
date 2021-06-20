package com.jlopez.bugtracker.converter;

import com.jlopez.bugtracker.entity.Bug;
import com.jlopez.bugtracker.entity.Status;
import com.jlopez.bugtracker.model.BugUpdateRequestPayload;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class BugUpdateRequestToBugConverter extends AbstractRegisterConverter<BugUpdateRequestPayload, Bug> {

    public BugUpdateRequestToBugConverter(ConversionService conversionService) {
        super(conversionService);
    }

    @Override
    public Bug convert(BugUpdateRequestPayload source) {
        Bug updatedBug = new Bug();
        updatedBug.setName(source.getName());
        updatedBug.setDescription(source.getDescription());
        updatedBug.setStatus(convert(source.getStatus(), Status.class));
        return updatedBug;
    }
}
