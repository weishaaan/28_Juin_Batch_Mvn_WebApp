package com.mora.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzTest implements Job {
          
    public void execute(JobExecutionContext context) throws JobExecutionException {
        
        System.out.println("running batch .");
        
        
    }
    
}
