package com.mora.controller;

import com.mora.service.BatchConfig;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;
import javax.xml.bind.JAXBException;
import javax.servlet.ServletContextEvent;
import org.apache.xmlbeans.XmlException;
 
public class MyServlet implements ServletContextListener {
    
    String fullPath;
    
    @Override
    public void contextInitialized(ServletContextEvent contextEvent){
        System.out.println("servlet for loading batch info.");
        
        ServletContext context = contextEvent.getServletContext();
        fullPath = context.getRealPath("/WEB-INF/batch_catalogue.xml");
        System.out.println("MyServlet full path is :"+fullPath);
        
        try {
            BatchConfig batchConfig = new BatchConfig(fullPath);
        } catch (XmlException | IOException | JAXBException ex) {
            Logger.getLogger(MyServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void contextDestroyed(ServletContextEvent arg0){
        System.out.print("servlet context destroyed.");
    }
    
    
    
}