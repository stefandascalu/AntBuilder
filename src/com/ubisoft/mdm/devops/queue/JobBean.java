package com.ubisoft.mdm.devops.queue;

import java.time.LocalDateTime;

import com.ubisoft.mdm.devops.enums.Environments;

/**
 * Bean that keeps all the information regarding the job that needs to be scheduled
 * @author sdascalu
 *
 */
public class JobBean {
	private String requester;
	private Environments environmentToBuild;
	private LocalDateTime requestingTime;

	public String getRequester() {
		return requester;
	}

	public void setRequester(String requester) {
		this.requester = requester;
	}

	public Environments getEnvironmentToBuild() {
		return environmentToBuild;
	}

	public void setEnvironmentToBuild(Environments environmentToBuild) {
		this.environmentToBuild = environmentToBuild;
	}

	public LocalDateTime getRequestingTime() {
		return requestingTime;
	}

	public void setRequestingTime(LocalDateTime requestingTime) {
		this.requestingTime = requestingTime;
	}

}
