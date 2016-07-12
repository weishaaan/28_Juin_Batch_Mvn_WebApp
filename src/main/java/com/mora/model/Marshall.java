/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mora.model;

import com.mora.controller.MyServlet;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Marshall {
    
    public Batches unmarshaller(String fullPath) throws JAXBException {
        
        String xmlPath = null;
        
        System.out.println("Marshall get path is :"+fullPath);
        
        //xmlPath = "src/main/webapp/WEB-INF/batch_catalogue.xml";
        JAXBContext jc = JAXBContext.newInstance(Batches.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        Batches batches = (Batches) unmarshaller.unmarshal(new File(fullPath));
        
        for (int i = 0; i < batches.getBatches().size(); i++) {
            Batch btc = batches.getBatches().get(i);
            System.out.println("Batch ");
            System.out.println("code is " +btc.getCode()+",name is"+btc.getName()+
                    ",description is "+btc.getDescription()+",category is "+btc.getCategory()
                    +",frequency is "+btc.getFrequency()+",input is ");
            if(btc.getInput().getParams() == null){
                 System.out.println(" NULL");
            }else{
                for (int j = 0; j < btc.getInput().getParams().size(); j++) {
                    Param p = btc.getInput().getParams().get(j);
                    System.out.println("-------------------");
                    System.out.println("the " + j + " param is : ");
                    System.out.println("param name is : " + p.PARAMNAME);
                    System.out.println("PARAM DEFAULT : " + p.DEFAULTVALUE);
                    System.out.println("PARAM label : " + p.LABEL);
                }
                System.out.println("*********************************");
            }
        }
        return batches;
    }
}
