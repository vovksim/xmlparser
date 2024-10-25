package com.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class XMLValidatorTest {
    private static final String SCHEMA = "schemas/devices.xsd";
    private static final String VALID_DATA = "xmlData/validDataInstance.xml";
    private static final String INVALID_DATA = "xmlData/invalidDataInstance.xml";

    @Test
    void validateBrokenXML() throws Exception {
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                XMLValidator.validate(SCHEMA, INVALID_DATA));
        assertEquals("Validation error! See logs for details.", exception.getMessage());
    }

    @Test
    void validateLegitXML() throws Exception {
        assertEquals(Boolean.TRUE, XMLValidator.validate(SCHEMA, VALID_DATA));
    }
}