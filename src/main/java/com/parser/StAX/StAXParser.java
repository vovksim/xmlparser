package com.parser.StAX;

import com.parser.DOM.AbstractDOMStAXHandler;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class StAXParser {
    XMLEventReader reader;

    public void parse(String xmlPath, AbstractDOMStAXHandler<?> handler) throws XMLStreamException {
        initReader(xmlPath);
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (event.getEventType() == XMLEvent.START_ELEMENT) {
                StartElement startElement = event.asStartElement();
                handler.initContainer(startElement.getName().getLocalPart());
                if (startElement.getAttributes() != null) {
                    for (Iterator<Attribute> it = startElement.getAttributes(); it.hasNext(); ) {
                        Attribute attribute = it.next();
                        Map<String, String> attributeNameValueMap = new HashMap<String, String>();
                        attributeNameValueMap.put(attribute.getName().getLocalPart(), attribute.getValue());
                        handler.mapAttributes(startElement.getName().getLocalPart(), attributeNameValueMap);
                    }
                }
                event = reader.nextEvent();
                if (event.getEventType() == XMLEvent.CHARACTERS) {
                    handler.mapValue(startElement.getName().getLocalPart(), event.asCharacters().getData());
                }
            }
        }
    }


    private void initReader(String xmlPath) throws XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        this.reader = factory.createXMLEventReader(new StreamSource(xmlPath));

    }
}
