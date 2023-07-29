package org.acme.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.acme.exception.ConverterException;
import org.acme.model.ConvertRequest;
import org.acme.model.ConvertResponse;
import org.junit.jupiter.api.Test;

class ConverterImplTest {
  ConverterImpl converter = new ConverterImpl();
  
  @Test
  void whenRequestUnitsAreNotValid() {
    ConverterException exception = assertThrows(ConverterException.class,
      () -> converter.convertUnit(new ConvertRequest(0F, "TEST", "test")));
    assertEquals("Invalid Operation 'TEST' to 'test'", exception.getMessage());
  }

  @Test
  void convertCelsiusToFahrenheit() {
    ConvertResponse result = converter.convertUnit(new ConvertRequest(100F, "celsius", "FAHRENHEIT"));
    assertEquals("FAHRENHEIT", result.unit());
    assertEquals(212F, result.value());
  }
}
