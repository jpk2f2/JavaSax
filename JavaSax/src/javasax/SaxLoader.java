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
    
    //SaxNode root;
    
    //allow me to move root out of the inner class
    /*public SaxNode retRoot(){
        return this.root;
    }
    
    public void setRoot(SaxNode node){
        this.root = node;
    }*/
    
    
    public static SaxNode load(File xmlFile) throws Exception
    {
        SaxNode root = null;// 
        
     
        
            
        DefaultHandler handler = new DefaultHandler() {


        List<SaxNode> list = new ArrayList<>(); 

        SaxNode currentNode;
        SaxNode tempRoot;


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

                if(currentNode != null){
                    if(currentNode.getProp().get(qName) != null){
                        currentNode.getProp().get(qName).add(node);

                    }else{
                        ArrayList<SaxNode> tempList = new ArrayList<>();
                        tempList.add(node);
                        currentNode.getProp().put(qName, tempList);
                    }
                }
                currentNode = node;
            }

               @Override
            public void characters(char ch[], int start, int length) throws SAXException{
                String temp = new String(ch,start,length);
                currentNode.SetContent((currentNode.getContent()+temp).replace(" ","").replace("\n", ""));
            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                int length = list.size();
                SaxNode end = list.remove(length-1);
                if(end != null){
                    end.SetContent(end.getContent().trim());

                    if(!list.isEmpty()){
                        currentNode = list.get(list.size()-1);
                    }else{
                        root = end;
                        //setRoot(end);
                        //setRoot(end);
                        currentNode = null;
                    }
                }
                setRoot(tempRoot);
            }

        };
            try{
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser saxParser = factory.newSAXParser();
                //setRoot(tempRoot);
                saxParser.parse(xmlFile.getAbsoluteFile(), handler);
            
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(SaxLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(SaxLoader.class.getName()).log(Level.SEVERE, null, ex);
        } 
     //root = list.get(0);
     return root;   
    }
    
}
