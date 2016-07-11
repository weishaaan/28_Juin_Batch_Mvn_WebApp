package com.mora.model;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.PROPERTY)
public class Input {

    public List<Param> params = null;
    
    public Input(List<Param> params){
        this.params = params;
    }
    
    public Input(){
        
    }
       
    @XmlElement(name = "PARAM")
    public List<Param> getParams() {
        return params;
    }
    
    public void setParams(List<Param> params) {
        this.params = params;
    }
}
