package com.mora.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"PARAMNAME", "DEFAULTVALUE", "LABEL", "INPUTVALUE"})
public class Param {

    public String PARAMNAME;
    public String DEFAULTVALUE;
    public String LABEL;
    public String INPUTVALUE;
    
    public Param(String PARAMNAME, String DEFAULTVALUE, String LABEL) {
        this.PARAMNAME = PARAMNAME;
        this.DEFAULTVALUE = DEFAULTVALUE;
        this.LABEL = LABEL;
    }

    public Param() {
    }

    @XmlElement(name = "PARAMNAME")
    public String getPARAMNAME() {
        return PARAMNAME;
    }

    @XmlElement(name = "DEFAULTVALUE")
    public String getDEFAULTVALUE() {
        return DEFAULTVALUE;
    }

    @XmlElement(name = "LABEL")
    public String getLABEL() {
        return LABEL;
    }

    @XmlElement(name = "INPUTVALUE")
    public String getINPUTVALUE() {
        return INPUTVALUE;
    }

    public void setPARAMNAME(String PARAMNAME) {
        this.PARAMNAME = PARAMNAME;
    }

    public void setDEFAULTVALUE(String DEFAULTVALUE) {
        this.DEFAULTVALUE = DEFAULTVALUE;
    }

    public void setLABEL(String LABEL) {
        this.LABEL = LABEL;
    }

    public void setINPUTVALUE(String INPUTVALUE) {
        this.INPUTVALUE = INPUTVALUE;
    }
    
}
