package com.ubisoft.mdm.devops.services;

import java.util.logging.Logger;

import com.ubisoft.mdm.devops.enums.JobStatus;
import com.ubisoft.mdm.devops.queue.BuildQueues;
import com.ubisoft.mdm.devops.queue.JobBean;

public class BuildQueueService {
	
	Logger logger = Logger.getLogger("BuildQueueService");
	private static final String CLASS_NAME = BuildQueueService.class.getName();
	
	/**
	 * This method will be used to add a job in queue
	 * @param jobBean
	 */
	public void addToQueue(JobBean jobBean){
		String methodName = "addToQueue";
		logger.entering(CLASS_NAME, methodName);
		
		BuildQueues buildQueues = BuildQueues.getInstance();
		buildQueues.getPendingJobs().push(jobBean);
		
		logger.exiting(CLASS_NAME, methodName);
	}
	
	/**
	 * This method will be used to check if a job is already in the queue
	 * @param jobBean
	 * @return
	 */
	public boolean checkIfAlreadyInQueue(JobBean jobBean){
		String methodName = "checkIfAlreadyInQueue";
		logger.entering(CLASS_NAME, methodName);
		
		BuildQueues buildQueues = BuildQueues.getInstance();
		if(buildQueues.getPendingJobs().contains(jobBean)){
			return true;
		}
		
		logger.exiting(CLASS_NAME, methodName);
		return false;
	}
	
	/**
	 * This method will be used to find in which status a job is at the moment
	 * @param jobBean
	 * @return
	 */
	public JobStatus checkJobStatus(JobBean jobBean){
		String methodName = "checkJobStatus";
		logger.entering(CLASS_NAME, methodName);
		
		JobStatus jobStatus = null;
		
		BuildQueues buildQueues = BuildQueues.getInstance();
		if(buildQueues.getPendingJobs().contains(jobBean)){
			jobStatus = JobStatus.PENDING;
		}
		else{
			if(buildQueues.getInProgressJobs().contains(jobBean)){
				jobStatus = JobStatus.IN_PROGRESS;
			}
			else{
				if(buildQueues.getFailedJobs().contains(jobBean)){
					jobStatus = JobStatus.FAILED;
				}
				else{
					jobStatus = JobStatus.FINISHED;
				}
			}
		}
		
		logger.exiting(CLASS_NAME, methodName);
		return jobStatus;
	}
}
