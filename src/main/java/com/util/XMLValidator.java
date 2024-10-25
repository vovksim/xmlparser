package com.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XMLValidator {
    public static boolean validate(String xsd, String xml) throws SAXException, IOException {
        Validator validator = getSchema(xsd).newValidator();
        XMLErrorHandler errorHandler = new XMLErrorHandler();
        validator.setErrorHandler(errorHandler);
        validator.validate(new StreamSource(new File(xml)));
        if (!errorHandler.isValid()) {
            throw new RuntimeException("Validation error! See logs for details.");
        }
        return true;
    }

    private static Schema getSchema(String xsd) throws SAXException {
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        return sf.newSchema(new File(xsd));
    }

    public static class XMLErrorHandler implements ErrorHandler {
        private static final Logger logger = LoggerFactory.getLogger(XMLValidator.XMLErrorHandler.class);
        private boolean isValid;

        public XMLErrorHandler() {
            this.isValid = true;
        }

        @Override
        public void warning(SAXParseException exception) {
            logger.warn("\nLine: {} Column: {} \n{}",
                    exception.getLineNumber(),
                    exception.getColumnNumber(),
                    exception.getMessage()
            );
            this.isValid = false;
        }

        @Override
        public void error(SAXParseException exception) {
            logger.error("\nLine: {} Column: {} \n{}",
                    exception.getLineNumber(),
                    exception.getColumnNumber(),
                    exception.getMessage()
            );
            this.isValid = false;
        }

        @Override
        public void fatalError(SAXParseException exception) throws SAXParseException {
            logger.error("FATAL_ERROR\nLine: {} Column: {} \n{}",
                    exception.getLineNumber(),
                    exception.getColumnNumber(),
                    exception.getMessage()
            );
            throw exception;
        }

        public boolean isValid() {
            return this.isValid;
        }
    }
}
