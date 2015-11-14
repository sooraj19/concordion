package org.concordion.internal.listener;

import nu.xom.Document;
import nu.xom.Element;

import org.concordion.api.listener.DocumentParsingListener;
import org.concordion.internal.util.Check;

public class StylesheetEmbedder implements DocumentParsingListener {

    private final String stylesheetContent;
    private final boolean appendChild;

    public StylesheetEmbedder(String stylesheetContent) {
        this.stylesheetContent = stylesheetContent;
        this.appendChild = false;
    }
    
    public StylesheetEmbedder(String stylesheetContent, boolean appendChild) {
        this.stylesheetContent = stylesheetContent;
        this.appendChild = appendChild;
    }
    
    public void beforeParsing(Document document) {
        Element html = document.getRootElement();
        Element head = html.getFirstChildElement("head");
        Check.notNull(head, "<head> section is missing from document");
        Element style = new Element("style");
        style.appendChild(stylesheetContent);
        
        if (appendChild) {
        	head.appendChild(style);
        } else {
        	head.insertChild(style, 0);
        }
    }
}