package com.mora.service;

import com.mora.batch.BatchDatabase;
import com.mora.model.Batch;
import com.mora.model.Batches;
import com.mora.model.Marshall;
import com.mora.model.Param;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBException;
import org.apache.xmlbeans.XmlException;

public class BatchConfig {

    public Map<String, Batch> catalogue = BatchDatabase.getMapBatches();
    
    public BatchConfig(String fullPath) throws XmlException, IOException, JAXBException {

        Marshall m = new Marshall();
        Batches batches = m.unmarshaller(fullPath);
        System.out.println("BatchConfig get path is :"+fullPath);
        
        for (int i = 0; i < batches.getBatches().size(); i++) {
            
            Batch batch = batches.getBatches().get(i);
            catalogue.put(batch.getCode(), batch);
            
            Map<String,Param> pl = new HashMap();
            
            if(batch.getInput().getParams() != null){
                for(int j = 0; j < batch.getInput().getParams().size();j++){
                    Param param = batch.getInput().getParams().get(j);
                    String paramName = param.PARAMNAME;
                    pl.put(paramName, param);
                }
            }
            catalogue.get(batch.getCode()).setParalist(pl);
        }
    }
}
