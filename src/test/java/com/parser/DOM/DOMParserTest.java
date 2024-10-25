package com.parser.DOM;

import com.structure.Device;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DOMParserTest {
    private static final String DATA = "xmlData/validDataInstance.xml";
    AbstractDOMStAXHandler<Device> DOMStAXHandler = new DeviceDOMStAXHandler();
    DOMParser domParser = new DOMParser();

    @Test
    void parse() throws ParserConfigurationException, IOException, SAXException {
        domParser.parse(DATA, DOMStAXHandler);
        ArrayList<Device> parsedData = DOMStAXHandler.getResult();
        Device actualResult = parsedData.getFirst();

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