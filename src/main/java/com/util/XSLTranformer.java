package com.util;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class XSLTranformer {
    public static void transform(String xslPath, String xmlPath) throws TransformerException {
        File xmlFile = new File(xmlPath);
        File xsltFile = new File(xslPath);

        Source xmlSource = new StreamSource(xmlFile);
        Source xsltSource = new StreamSource(xsltFile);

        TransformerFactory factory = TransformerFactory.newInstance();

        Transformer transformer = factory.newTransformer(xsltSource);

        StreamResult result = new StreamResult(new File("output.xml"));  // Outputs to XML file

        transformer.transform(xmlSource, result);
    }
}

