package com.delta.rm.ancillary.offer.mapping.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.delta.rm.ancillary.logger.AncLogger;
import com.delta.rm.ancillary.offer.mapping.interfaces.ClassMappingEndpoint;
import com.delta.rm.ancillary.offer.mapping.model.ClassMapping;
import com.delta.rm.ancillary.offer.mapping.model.ClassMappingRequest;
import com.delta.rm.ancillary.offer.mapping.repository.ClassMappingRepository;
import com.delta.rm.ancillary.offer.mapping.util.CommonConstant;

/**
 * Class ClassMappingController
 *
 */
@RestController
public class ClassMappingController implements ClassMappingEndpoint {
    
    /** the object that has the info about: ancLogger*/
	protected AncLogger ancLogger;
	
	/** the object that has the info about: jobLauncher*/
    private JobLauncher jobLauncher;	
    
    /** the object that has the info about: job*/
    private Job job;
    
    /** the object that has the info about: classMappingRepository*/
    private ClassMappingRepository classMappingRepository;
    
    /**
	 * @param ancLogger the ancLogger to set
	 */
	@Autowired 
	public void setAncLogger(AncLogger ancLogger) {
		this.ancLogger = ancLogger;
	}

    /**
	 * @param jobLauncher the jobLauncher to set
	 */
    @Autowired
	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}
	
    /**
	 * @param job the job to set
	 */
    @Autowired
	public void setJob(Job job) {
		this.job = job;
	}
	
    /**
	 * @param classMappingRepository the classMappingRepository to set
	 */
    @Autowired
	public void setClassMappingRepository(ClassMappingRepository classMappingRepository) {
		this.classMappingRepository = classMappingRepository;
	}

	/**
     * Endpoint to retrieve all ClassOfMapping
     * no arguments
     * @return object response
     */
    @Override
    public ResponseEntity<?> getAllClassofMapping(){
    	try {
    		return new ResponseEntity<>(classMappingRepository.findAll(), HttpStatus.OK);
    	} catch (Exception ex) {
			ancLogger.error("Exception getting Class mapping details: " , ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    /**
     * Endpoint to search ClassOfMapping
     * @param classMappingRequest object request
     * @return object response
     */
    @Override
    public ResponseEntity<?> getClassofMapping(@Valid @RequestBody final ClassMappingRequest classMappingRequest) throws Exception{
    	Map<String, String> mapNotFoundResponse = new HashMap<>();
    	try {
    		List<ClassMapping> response = classMappingRepository.findByCustomSearch(classMappingRequest.getMarketingCarrierCode(), 
    				                                                                classMappingRequest.getMarketingFlightNumber(),
    				                                                                classMappingRequest.getDepartureAirportCd(),
    				                                                                classMappingRequest.getArrivalAirportCd(),
    				                                                                classMappingRequest.getMarketingClassOfService(),
    				                                                                classMappingRequest.getDepartureDate());
    		Map<String,Object> processedResponse = processResponse(response, classMappingRequest);
    		boolean marketingCOSExists = (boolean) processedResponse.get("flag");
    		if(response.isEmpty() ) {
   			   mapNotFoundResponse.put("response", "Info is not available");
   			   return new ResponseEntity<>(mapNotFoundResponse, HttpStatus.NOT_FOUND);
   		    }
    		if (!marketingCOSExists) {
   			   mapNotFoundResponse.put("response", "Marketing Class of Service not found");
   			   return new ResponseEntity<>(mapNotFoundResponse, HttpStatus.NOT_FOUND);
    		} 
    		   return new ResponseEntity<>(processedResponse.get("listResponse"), HttpStatus.OK);
    	} catch (Exception ex) {
			ancLogger.error("Exception getting Class Mapping Info Info: " , ex);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    /**
     * Endpoint to perform bulkload on Cassandra database
     * no arguments
     * @return batch status
     */
    @Override
    public BatchStatus bulkload() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(job, parameters);
        ancLogger.info("JobExecution: " + jobExecution.getStatus());
        ancLogger.info("Batch is Running...");
        while (jobExecution.isRunning()) {
        	ancLogger.info("...");
        }
        return jobExecution.getStatus();
    }
    
    /**
     * Method to retrieve the appropriate OperatedClassOfService value
     * based on the MarketingClassOfService in the request
     * @param res object response
     * @param req object request
     * @return object response
     */
    private Map<String,Object> processResponse(List<ClassMapping> res, ClassMappingRequest req){
    	Map<String,Object> mapResponse = new HashMap<>();
    	List<ClassMapping> response = new ArrayList<>();
    	boolean foundMarketingCOS = false;
    	for(ClassMapping clRes: res) {
    		  int position = 0;
    		  String[] arrayMarketingCOS = clRes.getMarketingClassOfService().split(CommonConstant.PARAM_PIPE);
    		  for(int i=0; i < arrayMarketingCOS.length; i ++ ) {    		
    			      if(req.getMarketingClassOfService().equals(arrayMarketingCOS[i]) && !(CommonConstant.PARAM_HYPHEN).equals(req.getMarketingClassOfService()) ) {
    				        clRes.setMarketingClassOfService(arrayMarketingCOS[i]);
    				        position = i;
    				        foundMarketingCOS = true;
    				        break;
    			      }
    		  }    		
    		  		  String[] arrayOperatedCOS = clRes.getOperatedClassOfService().split(CommonConstant.PARAM_PIPE);
    		  		  if(  !(CommonConstant.PARAM_HYPHEN).equals( arrayOperatedCOS[position] ) && !(CommonConstant.PARAM_SPACE).equals(arrayOperatedCOS[position]) ) {
    		  			clRes.setOperatedClassOfService(arrayOperatedCOS[position]);
    		  		  }else {
    		            for(int j=0; j < arrayOperatedCOS.length; j ++ ) {
    			          if( ! (CommonConstant.PARAM_HYPHEN).equals(arrayOperatedCOS[j]) && !(CommonConstant.PARAM_SPACE).equals(arrayOperatedCOS[j]) ){
    			    	    clRes.setOperatedClassOfService(arrayOperatedCOS[j]);
   			           	    break;
    			          }
    		             }
    		  		  }
    	  response.add(clRes);
    	}
    	mapResponse.put("listResponse", response);
    	mapResponse.put("flag", foundMarketingCOS);
      return mapResponse;
    } 
}
