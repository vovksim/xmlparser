package com.parser;

import org.w3c.dom.Node;

import java.util.ArrayList;

public abstract class DOMAbstractClassMapper<T> {
    protected ArrayList<T> result;

    public ArrayList<T> getResult() {
        return result;
    }

    //mapping node attributes
    public abstract void mapAttributes(Node node);

    //mapping node value
    public abstract void mapValue(String tagName, String nodeValue);

    //init container for complex type node
    public abstract void initContainer(String containerNodeName);
}
