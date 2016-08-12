package com.ubisoft.mdm.devops.servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.ubisoft.mdm.devops.enums.Environments;
import com.ubisoft.mdm.devops.enums.JobStatus;
import com.ubisoft.mdm.devops.queue.JobBean;
import com.ubisoft.mdm.devops.services.BuildQueueService;

/**
 * Servlet implementation class BuildManagerServlet
 */
@WebServlet("/buildManager")
public class BuildManagerServlet extends MDMBaseServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger("BuildManagerServlet");
	private static final String CLASS_NAME = BuildManagerServlet.class.getName();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BuildManagerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		super.init();
		gson = registerGsonAdapters();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if(pathInfo.equals("/checkJobStatus")){
			checkJobStatus(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if (pathInfo.equals("/addToQueue")) {
			addToQueue(request, response);
		}
	}

	/**
	 * Method used to add a job to the queue
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void addToQueue(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String methodName = "addToQueue";
		logger.entering(CLASS_NAME, methodName);

		HttpSession session = request.getSession();

		String loggedInUser = (String) session.getAttribute("loggedInUser");
		int environmentToBuild = Integer.valueOf(request.getParameter("environment"));

		JobBean jobBean = new JobBean();
		jobBean.setRequester(loggedInUser);
		jobBean.setRequestingTime(LocalDateTime.now());

		boolean foundEnv = false;
		for (Environments env : Environments.values()) {
			if (environmentToBuild == env.getId()) {
				jobBean.setEnvironmentToBuild(env);
				foundEnv = true;
				break;
			}
		}

		if (foundEnv == false) {
			onError("The environment that you've choose doesn't exist. Please contact MDM DEVOS admins.", response);
		} else {
			BuildQueueService buildQueueService = new BuildQueueService();
			if (!buildQueueService.checkIfAlreadyInQueue(jobBean)) {
				buildQueueService.addToQueue(jobBean);
				onSuccess("A new task for building this environment has beend added to the pending queue", response);
			} else {
				onFail("A job for this environment has been already submitted!", response);
			}
		}

		logger.exiting(CLASS_NAME, methodName);
	}
	
	/**
	 * This method will check a job status 
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void checkJobStatus(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String methodName = "checkJobStatus";
		logger.entering(CLASS_NAME, methodName);
		
		int environmentToBuild = Integer.valueOf(request.getParameter("env"));
		Environments envToUse = null;
		for(Environments env : Environments.values()){
			if(env.getId() == environmentToBuild){
				envToUse = env;
				break;
			}
		}
		
		if(envToUse != null){
			BuildQueueService buildQueueService = new BuildQueueService();
			JobBean jobBean = new JobBean();
			jobBean.setEnvironmentToBuild(envToUse);
			JobStatus jobStatus = buildQueueService.checkJobStatus(jobBean);
			onSuccess(jobStatus, response);
		}
		else{
			onError("The environment you've offered doesn't exist.", response);
		}
		
		
		logger.exiting(CLASS_NAME, methodName);
	}

	@Override
	protected <T> void onSuccess(T responseObject, HttpServletResponse response) throws IOException {
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson.toJson(responseObject));

	}

	@Override
	protected <T> void onError(T responseObject, HttpServletResponse response) throws IOException {
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson.toJson(responseObject));

	}

	@Override
	protected <T> void onFail(T responseObject, HttpServletResponse response) throws IOException {
		response.setStatus(HttpServletResponse.SC_CONFLICT);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson.toJson(responseObject));

	}

	@Override
	protected Gson registerGsonAdapters() {
		return new Gson();
	}

}
