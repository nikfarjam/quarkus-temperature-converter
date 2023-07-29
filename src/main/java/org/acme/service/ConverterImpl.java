package org.acme.service;

import java.util.Optional;

import org.acme.exception.ConverterException;
import org.acme.model.ConvertRequest;
import org.acme.model.ConvertResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConverterImpl implements Converter {
    private Logger logger = LoggerFactory.getLogger(ConverterImpl.class);
    
    @Override
    public ConvertResponse convertUnit(ConvertRequest req) {
        logger.debug("Trying to process {}", req);
        final var from = Optional.of(req).map(ConvertRequest::from).orElse("");
        final var to = Optional.of(req).map(ConvertRequest::to).orElse("");
        
        final var value = switch (String.join("_", from, to).toUpperCase()) {
            case "CELSIUS_FAHRENHEIT" -> ((Optional.of(req).map(ConvertRequest::value).orElse(0F)) * 9 / 5) + 32F;
            case "FAHRENHEIT_CELSIUS" -> ((Optional.of(req).map(ConvertRequest::value).orElse(0F)) - 32) * 5 / 9;
            default -> throw new ConverterException("Invalid Operation '" + from + "' to '" + to + "'");
        };
        return new ConvertResponse(value, to);
    }
}
