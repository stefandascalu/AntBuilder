package com.ubisoft.mdm.devops.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public abstract class MDMBaseServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public static final String MDM_DEVOPS_STANDARD_ERROR_MESSAGE = "An error occured! Please contact MDM DEVOPS Administrators";
	protected Gson gson = null;
	
	/**
	 * This method will be used to encapsulate the message that is sent by an MDMBaseServlet in case of success
	 * @param responseObject
	 * @param response
	 * @throws IOException
	 */
	protected abstract <T> void onSuccess(T responseObject, HttpServletResponse response) throws IOException;
	
	/**
	 * This method will be used to encapsulate the message that is sent by an MDMBaseServlet in case of error
	 * @param responseObject
	 * @param response
	 * @throws IOException
	 */
	protected abstract <T> void onError(T responseObject, HttpServletResponse response) throws IOException;
	
	/**
	 * This method will be used to encapsulate the message that is sent by an MDMBaseServlet in case of failure
	 * @param responseObject
	 * @param response
	 * @throws IOException
	 */
	protected abstract <T> void onFail(T responseObject, HttpServletResponse response) throws IOException;
	
	/**
	 * Will be used to register custom Gson Adapters
	 * @return
	 */
	protected abstract Gson registerGsonAdapters();
	
}
