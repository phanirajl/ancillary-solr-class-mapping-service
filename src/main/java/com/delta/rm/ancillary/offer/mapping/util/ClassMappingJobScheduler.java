package com.delta.rm.ancillary.offer.mapping.util;

import java.util.HashMap;
import java.util.Map;

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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.delta.rm.ancillary.logger.AncLogger;

@Component
public class ClassMappingJobScheduler {
	
	@Autowired 
	protected AncLogger ancLogger;

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	/**@Qualifier("writerJob")*/
	Job jobClassMappingWriter;

	@Scheduled(fixedDelay = 2000000)
	public void load() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException {

		Map<String, JobParameter> maps = new HashMap<>();
		maps.put("time", new JobParameter(System.currentTimeMillis()));

		JobParameters parameters = new JobParameters(maps);
		JobExecution jobExecution = jobLauncher.run(jobClassMappingWriter, parameters);
		ancLogger.info("JobExecution: " + jobExecution.getStatus());
		ancLogger.info("Batch is Running...");
		while (jobExecution.isRunning()) {
			ancLogger.info("...");
		}

	}

}
