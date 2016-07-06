package com.mora.model;

import com.mora.quartz.QuartzTest;
import com.mora.quartz.QuartzTrigger;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.management.monitor.Monitor;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.PUT;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import static javax.ws.rs.client.Entity.json;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.bind.JAXBException;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlException;
import org.eclipse.jetty.server.Request;
import static org.glassfish.jersey.server.model.Parameter.Source.PATH;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Path("home")
public class GenericResource {
    
    BatchDatabase batchDatabase = new BatchDatabase();
    Test_property property = new Test_property();  
    private static Logger logger = Logger.getLogger(BatchService.class);
    QuartzTrigger trigger = new QuartzTrigger();

    QuartzTest quartz = new QuartzTest();
    
   
    /*************************** JSON **************************************/
    @GET
    @Path("getOneBatch/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Batch getBatchByCode(@PathParam("code") String code)throws IOException, XmlException{
        return batchDatabase.getBatch(code);
    }
    
    
    @GET
    @Path("getAllBatch")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Batch> getAllBatch() throws IOException{
        return batchDatabase.getAllBatches();
    }

    @GET
    @Path("Batch")
    @Produces(MediaType.TEXT_PLAIN)
    public String doGetBat(){            
        //r.createProperties();        
        String filepath = property.readProperties("test");
        //System.out.println(filepath);
        String result = property.runBatFile(filepath);                        
	return result + " ! it works!!! ";      
    }
    
    @POST
    @Path("runBatch")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String runBat(Batch batch){            
        //r.createProperties();     
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
            // String filepath = property.readProperties("text");
            String result = property.runBatFile(filepath);
            
            logger.info("Run batch file successsfully!");
            logger.info("The batch code is: "+ btc.code + ".");
            logger.info("This batch file doesn't have any params.");
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
        // String filepath = property.readProperties("text");
        String result = property.runBatFile(filepath);
        
        Gson gson = new Gson();
        String paramPostList = gson.toJson(paramPosts);
        logger.info("Successfully $POST params to web service!"+"The batch code is " + btc.code + "Those params are: "+ paramPostList);
        
        //[{"paramCode":"07J"},{"refMora":"changed"},{"date1":"1"},{"date2":"2"}]
        Map<String,Param> paraList = btc.paralist;
        for(int j = 1 ;j< paramPosts.size(); j++){
            Param p = btc.input.getParams().get(j-1);
            p.setDEFAULTVALUE(paramPosts.get(j).INPUTVALUE);
            paraList.put(btc.input.getParams().get(j-1).PARAMNAME, p);
        }
        
        return    "Run batch, and the result is : "+result; 
        
        /*
        String filepath = r.readProperties("batch_name.bat");
        System.out.println(filepath);
        filepath = filepath +"  \"" + default_value + "\"" ;
        String result = r.runBatFile(filepath);                        
	return  result ; 

        //return  "Post succesfully! the changed default value is :"+ btc.getInput().getParams().get(0).DEFAULTVALUE;  
        */
    }
    
    
}
    
    
 

