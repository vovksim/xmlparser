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
import java.util.List;
import java.util.ArrayList;

public class XmlValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(XmlValidator.class);

    private static class XmlErrorHandler implements ErrorHandler {
        private ArrayList<SAXParseException> exceptions;

        public XmlErrorHandler() {
            exceptions = new ArrayList<>();
        }

        @Override
        public void warning(SAXParseException exception) throws SAXParseException {
            exceptions.add(exception);
        }

        @Override
        public void error(SAXParseException exception) throws SAXParseException {
            exceptions.add(exception);
        }

        @Override
        public void fatalError(SAXParseException exception) throws SAXParseException {
            throw exception;
        }

        public List<SAXParseException> getExceptions() {
            return exceptions;
        }
    }

    public static void validate(String schemaPath, String dataPath) throws SAXException, IOException {
        XmlErrorHandler xsdErrorHandler = new XmlErrorHandler();
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File(schemaPath));
        Validator validator = schema.newValidator();
        validator.setErrorHandler(xsdErrorHandler);
        validator.validate(new StreamSource(new File(dataPath)));
        if (!xsdErrorHandler.getExceptions().isEmpty()) {
            for (SAXParseException exception : xsdErrorHandler.getExceptions()) {
                LOGGER.error("\nFile: {} \nLine: {} Column: {} \n{}",
                        dataPath,
                        exception.getLineNumber(),
                        exception.getColumnNumber(),
                        exception.getMessage()
                );
            }
            throw new SAXException("Validation failed. See logs for details.");
        }
    }
}
