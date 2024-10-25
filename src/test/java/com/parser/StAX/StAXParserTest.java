package com.parser.StAX;

import com.parser.DOM.AbstractDOMStAXHandler;
import com.parser.DOM.DeviceDOMStAXHandler;
import com.structure.Device;
import org.junit.jupiter.api.Test;

import javax.xml.stream.XMLStreamException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StAXParserTest {
    private static final String DATA = "xmlData/validDataInstance.xml";

    private static final StAXParser staxParser = new StAXParser();
    AbstractDOMStAXHandler<Device> DOMStAXHandler = new DeviceDOMStAXHandler();


    @Test
    void parse() throws XMLStreamException {
        staxParser.parse(DATA, DOMStAXHandler);
        Device actualResult = DOMStAXHandler.getResult().getFirst();
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