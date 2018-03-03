/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasax;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Jason
 */
public class SaxLoader {
    
    public static SaxNode load(File xmlFile) throws Exception
    {
        SaxNode root;// = new SaxNode();
        List<SaxNode> list = new ArrayList<>(); 
        
        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            
            DefaultHandler handler = new DefaultHandler() {
                
                
                
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
                    SaxNode node = new SaxNode();
                    node.SetName(qName);
                    LinkedHashMap<String,String> tempHash = new LinkedHashMap<>();
                    
                    for(int i = 0; i<attributes.getLength();i++){
                        tempHash.put(attributes.getQName(i), attributes.getValue(i));
                        //node.attributes.put(attributes.getQName(i), attributes.getValue(i));
                    }
                    
                    node.setAtt(tempHash);
                    list.add(node);
                    
                }
                
                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    int length = list.size();
                    if(!list.isEmpty()){
                        list.remove(length-1);
                    }
                }
            };
            
            saxParser.parse(xmlFile.getAbsoluteFile(), handler);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(SaxLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(SaxLoader.class.getName()).log(Level.SEVERE, null, ex);
        } 
     root = list.get(0);
     return root;   
    }
}
