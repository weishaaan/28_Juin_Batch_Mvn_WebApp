package com.mora.model;

import com.mora.service.BatchConfig;
import com.mora.batch.BatchDatabase;
import com.mora.batch.BatchProcessor;
import com.mora.quartz.QuartzTest;
import com.mora.quartz.QuartzTrigger;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlException;
import com.google.gson.Gson;
import java.util.Map;

@Path("home")
public class GenericResource {
    
    BatchDatabase batchDatabase = new BatchDatabase();
    BatchProcessor batchProcessor = new BatchProcessor();  
    private static Logger logger = Logger.getLogger(BatchConfig.class);
    QuartzTrigger trigger = new QuartzTrigger();
    QuartzTest quartz = new QuartzTest();
    
    @GET
    @Path("getAllBatch")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Batch> getAllBatch() throws IOException{
        return batchDatabase.getAllBatches();
    }
    
    @POST
    @Path("runBatch")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String runBat(Batch batch){            
        
        Batch btc = new Batch();
        try {
            btc = batchDatabase.getBatch(batch.code);
            
        } catch (XmlException ex) {
            java.util.logging.Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Wrong, can't $POST successfully to web service."); 
            
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Wrong, can't $POST successfully to web service.");  
        }
            String filepath = btc.getBatpath();
            String result = batchProcessor.runBatFile(filepath);
            
            logger.info("Successfully $POST params to web service!"+"The batch code is: "+ btc.code + "."+"This batch file doesn't have any params.");
            logger.info("The running result is: "+ result + ".");
            
            return    "Run batch, and the result is : "+result; 
    }
    
    @GET
    @Path("quartz")
    @Produces(MediaType.TEXT_PLAIN)
    public String doQuartzTrigger(){           
        try {
            
            String frequency = batchDatabase.getBatch("08M").getFrequency();
            trigger.QuartzTrigger(frequency);
            
            System.out.println("quartz start works!" );
            
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
	return "quartz works!";      
    }
    
    @POST
    @Path("postTestBatchParam")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String postNameRunBat(List<Param> paramPosts){    
        
        Batch btc= new Batch();
        
        try {
            btc = batchDatabase.getBatch(paramPosts.get(0).INPUTVALUE);
        } catch (XmlException ex) {
            java.util.logging.Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Wrong, can't get the batch file."); 
            
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Wrong, can't get the batch file"); 
        }
        
        String filepath = btc.getBatpath();
        String result = batchProcessor.runBatFile(filepath);
        
        Gson gson = new Gson();
        String paramPostList = gson.toJson(paramPosts);
        logger.info("Successfully $POST params to web service!"+"The batch code is " + btc.code + ".Those params are: "+ paramPostList);
        logger.info("The running result is: "+ result + ".");
        
        Map<String,Param> paraList = btc.paralist;
        for(int j = 1 ;j< paramPosts.size(); j++){
            Param p = btc.input.getParams().get(j-1);
            p.setDEFAULTVALUE(paramPosts.get(j).INPUTVALUE);
            paraList.put(btc.input.getParams().get(j-1).PARAMNAME, p);
        }
        
        return    "Run batch, and the result is : "+result; 
        
    }
    
    
}
    
    
 

