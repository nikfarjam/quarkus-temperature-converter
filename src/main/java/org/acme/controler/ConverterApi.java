package org.acme.controler;

import org.acme.model.ConvertRequest;
import org.acme.model.ConvertResponse;
import org.acme.service.Converter;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/converter")
public class ConverterApi {

  @Inject
  private Converter converter;
    
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ConvertResponse convert(ConvertRequest request) {
    return converter.convertUnit(request);
  }
}
