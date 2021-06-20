package com.jlopez.bugtracker.converter;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterRegistry;

import javax.annotation.PostConstruct;

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

}