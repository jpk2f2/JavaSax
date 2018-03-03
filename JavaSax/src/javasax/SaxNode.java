/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javasax;

import java.util.List;
import java.util.LinkedHashMap;

/**
 *
 * @author Jason
 */
public class SaxNode {
    private String name;
    private String content;
    private LinkedHashMap<String,List<SaxNode>> properties;
    private LinkedHashMap<String,String> attributes;
    
    public SaxNode(){
        this.name = "";
        this.content = "";
        this.properties = new LinkedHashMap<>();
        this.attributes = new LinkedHashMap<>();
    }
    
    public void SetName(String name){
        this.name = name; 
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setAtt(LinkedHashMap<String,String> att){
        this.attributes = att;
    }
    
    public LinkedHashMap<String,String> getAtt(){
        return this.attributes;
    }
    
    public void setProp(LinkedHashMap<String,List<SaxNode>> prop){
        this.properties = prop;
    }
    
    public LinkedHashMap<String,List<SaxNode>> getProp(){
        return this.properties;
    }
    
    public void SetContent(String content){
        this.content = content; 
    }
    
    public String getContent(){
        return this.content;
    }
}
