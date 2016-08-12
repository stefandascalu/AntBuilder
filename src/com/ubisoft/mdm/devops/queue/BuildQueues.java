package com.ubisoft.mdm.devops.queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Singleton class that will be used to manage the background build process
 * 
 * @author sdascalu
 *
 */
public class BuildQueues {

	private static BuildQueues instance = null;

	private Deque<JobBean> pendingJobs = new ArrayDeque<>();
	private List<JobBean> inProgressJobs = new ArrayList<>();
	private List<JobBean> finishedJobs = new ArrayList<>();

	private List<FailedJobBean> failedJobs = new ArrayList<>();

	private BuildQueues() {

	}

	public static BuildQueues getInstance() {
		if (instance == null) {
			instance = new BuildQueues();
		}
		return instance;
	}

	public Deque<JobBean> getPendingJobs() {
		return pendingJobs;
	}

	public void setPendingJobs(Deque<JobBean> pendingJobs) {
		this.pendingJobs = pendingJobs;
	}

	public List<JobBean> getInProgressJobs() {
		return inProgressJobs;
	}

	public void setInProgressJobs(List<JobBean> inProgressJobs) {
		this.inProgressJobs = inProgressJobs;
	}

	public List<JobBean> getFinishedJobs() {
		return finishedJobs;
	}

	public void setFinishedJobs(List<JobBean> finishedJobs) {
		this.finishedJobs = finishedJobs;
	}

	public List<FailedJobBean> getFailedJobs() {
		return failedJobs;
	}

	public void setFailedJobs(List<FailedJobBean> failedJobs) {
		this.failedJobs = failedJobs;
	}

}
