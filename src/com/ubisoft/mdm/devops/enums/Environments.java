package com.ubisoft.mdm.devops.enums;

/**
 * Enum that will be used to identify each environment
 * 
 * @author sdascalu
 *
 */
public enum Environments {

	DST(1, "DST"), TST(2, "TST"), UAT(3, "UAT"), PROD(4, "PROD");

	private int id;
	private String name;

	private Environments(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
