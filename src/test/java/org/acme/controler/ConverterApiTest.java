package org.acme.controler;

import org.acme.model.ConvertError;
import org.acme.model.ConvertRequest;
import org.acme.model.ConvertResponse;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import static jakarta.ws.rs.core.HttpHeaders.*;
import static jakarta.ws.rs.core.MediaType.*;
import static jakarta.ws.rs.core.Response.Status.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;


@QuarkusTest
class ConverterApiTest {

    private Jsonb jsonb = JsonbBuilder.create();

    @Test
    void whenConvertInputIsEmpty() {
        given()
                .header(ACCEPT, APPLICATION_JSON)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .body(jsonb.toJson(new ConvertRequest(0F, null, null)))
                .when()
                .post("/converter")
                .then()
                .statusCode(INTERNAL_SERVER_ERROR.getStatusCode())
                .body(is(jsonb.toJson(
                        new ConvertError("Internal error", "Invalid Operation '' to ''"))));
    }

    @Test
    void convertCelsiusToFahrenheit() {
        given()
                .header(ACCEPT, APPLICATION_JSON)
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .body(jsonb.toJson(new ConvertRequest(100F, "celsius", "fahrenheit")))
                .when()
                .post("/converter")
                .then()
                .statusCode(OK.getStatusCode())
                .body(is(jsonb.toJson(new ConvertResponse(212F, "fahrenheit"))));
    }
}
