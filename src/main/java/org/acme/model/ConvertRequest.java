package org.acme.model;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record ConvertRequest(Float value, String from, String to) {}
