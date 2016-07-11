package com.mora.batch;

import java.io.IOException;
import java.util.ArrayList;
import com.mora.model.*;
import com.mora.service.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.xmlbeans.XmlException;

public class BatchDatabase {
    public static Map<String,Batch> catalogue = new HashMap<String,Batch>();

    public static Map<String,Batch> getMapBatches(){
        return catalogue;
    }
    
    public List<Batch> getAllBatches() {
        
        return new ArrayList<Batch>(catalogue.values());
        
    }

    public Batch getBatch(String code) throws XmlException, IOException {
        
        Batch b = catalogue.get(code);
        
        if(b!= null)
            return b;
        else 
            return null;
    }
}
