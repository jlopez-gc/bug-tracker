package com.jlopez.bugtracker.converter;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterRegistry;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractRegisterConverter<S, T> implements Converter<S, T> {

    private final ConversionService conversionService;

    public AbstractRegisterConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @PostConstruct
    private void register() {
        if (this.conversionService != null) {
            ((ConverterRegistry) this.conversionService).addConverter(this);
        } else {
            throw new IllegalStateException("Can't register Converter to ConverterRegistry");
        }
    }

    protected <U> U convert(Object obj, Class<U> vClass) {
        return this.conversionService.convert(obj, vClass);
    }

    protected <U> Set<U> convert(Set<?> collection, Class<U> clazz) {
        return collection.stream()
                .map(item -> this.convert(item, clazz))
                .collect(Collectors.toSet());
    }

    protected <U> List<U> convert(List<?> collection, Class<U> clazz) {
        return collection.stream()
                .map(item -> this.convert(item, clazz))
                .collect(Collectors.toList());
    }
}