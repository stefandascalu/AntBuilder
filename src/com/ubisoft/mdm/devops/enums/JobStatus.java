package com.ubisoft.mdm.devops.enums;

/**
 * Enum used for the status of a job
 * @author sdascalu
 *
 */
public enum JobStatus {

	PENDING(1), IN_PROGRESS(2), FAILED(3), FINISHED(4);

	private int code;

	private JobStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
