package org.acme.service;

import org.acme.model.ConvertRequest;
import org.acme.model.ConvertResponse;

public interface Converter {
    ConvertResponse convertUnit(ConvertRequest req);
}
