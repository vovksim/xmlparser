package com.parser.DOM;

import java.util.ArrayList;
import java.util.Map;

// We are storing List of T type, which is child type for root node
// in this case root:Devices, then T:Device
public abstract class AbstractDOMStAXHandler<T> {
    protected ArrayList<T> result;

    public ArrayList<T> getResult() {
        return result;
    }

    //mapping node attributes
    public abstract void mapAttributes(String tagName, Map<String, String> attributeNameValueMap);

    //mapping node value
    public abstract void mapValue(String tagName, String nodeValue);

    //init container for complex type node
    public abstract void initContainer(String containerNodeName);
}
