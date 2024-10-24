package com.edu;

import com.parser.DOM.AbstractDOMStAXHandler;
import com.parser.DOM.DOMParser;
import com.parser.DOM.DeviceDOMStAXHandler;
import com.parser.SAX.SAXDeviceHandler;
import com.parser.StAX.StAXParser;
import com.structure.Device;
import com.util.XMLValidator;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static final String DATA_PATH = "test1instance.xml";
    static final String SCHEMA_PATH = "devices.xsd";

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, XMLStreamException {
        XMLValidator.validate(SCHEMA_PATH, DATA_PATH);

        DOMParser parser = new DOMParser();
        AbstractDOMStAXHandler<Device> deviceDOMHandler = new DeviceDOMStAXHandler();

        parser.parse(DATA_PATH, deviceDOMHandler);
        System.out.println(deviceDOMHandler.getResult());


        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        SAXDeviceHandler saxDeviceHandler = new SAXDeviceHandler();
        saxParser.parse(DATA_PATH, saxDeviceHandler);
        System.out.println(saxDeviceHandler.getResult());

        StAXParser staxParser = new StAXParser();
        staxParser.parse(DATA_PATH, deviceDOMHandler);
        System.out.println(deviceDOMHandler.getResult());
    }
}