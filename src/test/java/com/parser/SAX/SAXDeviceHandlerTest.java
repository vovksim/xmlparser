package com.parser.SAX;

import com.structure.Device;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SAXDeviceHandlerTest {
    private static final String DATA = "xmlData/validDataInstance.xml";

    @Test
    public void parse() throws ParserConfigurationException, SAXException, IOException {
        SAXDeviceHandler handler = new SAXDeviceHandler();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(new File(DATA), handler);
        Device actualResult = handler.getResult().getFirst();

        assertEquals(actualResult.getName(), "Monitor");
        assertEquals(actualResult.getOrigin(), "China");
        assertEquals(actualResult.getPrice(), 100);
        assertEquals(actualResult.getCritical(), Boolean.TRUE);
        assertEquals(actualResult.getDetails().isPeripheral(), Boolean.TRUE);
        assertEquals(actualResult.getDetails().getEnergyConsumption(), 100);
        assertEquals(actualResult.getDetails().isHasCooler(), Boolean.TRUE);
        assertEquals(actualResult.getDetails().getGroup(), "Multimedia");
        assertEquals(actualResult.getDetails().getPorts().get(0).getPortType(), "USB");
        assertEquals(actualResult.getDetails().getPorts().get(0).getQuantity(), 2);
        assertEquals(actualResult.getDetails().getPorts().get(1).getPortType(), "HDMI");
        assertEquals(actualResult.getDetails().getPorts().get(1).getQuantity(), 1);
    }

}