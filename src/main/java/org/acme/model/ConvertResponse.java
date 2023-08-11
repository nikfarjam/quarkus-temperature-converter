package org.acme.model;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record ConvertResponse(Float value, String unit) {}