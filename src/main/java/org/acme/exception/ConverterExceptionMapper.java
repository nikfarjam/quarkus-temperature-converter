package org.acme.exception;

import java.util.Optional;

import org.acme.model.ConvertError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConverterExceptionMapper implements ExceptionMapper<ConverterException> {
  private final Logger logger = LoggerFactory.getLogger(ConverterExceptionMapper.class);
    
  @Override
  public Response toResponse(ConverterException e) {
    logger.error("An internal error occurred", e);
    return Response
             .status(Response.Status.INTERNAL_SERVER_ERROR)
             .entity(new ConvertError("Internal error", Optional.of(e).map(Throwable::getMessage).orElse("Internal error")))
             .build();
  }
}
