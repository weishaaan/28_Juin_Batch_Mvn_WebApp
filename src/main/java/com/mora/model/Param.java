package com.mora.model;

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

    public void setPARAMNAME(String PARAMNAME) {
        this.PARAMNAME = PARAMNAME;
    }

    public void setDEFAULTVALUE(String DEFAULTVALUE) {
        this.DEFAULTVALUE = DEFAULTVALUE;
    }

    public void setLABEL(String LABEL) {
        this.LABEL = LABEL;
    }
}
