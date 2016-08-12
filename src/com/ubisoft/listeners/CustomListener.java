package com.ubisoft.listeners;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.tools.ant.BuildEvent;
import org.apache.tools.ant.BuildListener;

public class CustomListener implements BuildListener{

	HttpServletResponse servletResponse;
	
	public CustomListener(HttpServletResponse servletResponse) {
		this.servletResponse = servletResponse;
	}
	
	@Override
	public void buildFinished(BuildEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("has finished");
		PrintWriter out;
		try {
			out = this.servletResponse.getWriter();
			out.println("<html>");
			out.println("<body>");
			out.println("<h1>Hello Servlet Get</h1>");
			out.println("</body>");
			out.println("</html>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void buildStarted(BuildEvent arg0) {
		System.out.println(arg0.getProject() + " has started");
		
		PrintWriter out;
		try {
			out = this.servletResponse.getWriter();
			out.println("<html>");
			out.println("<body>");
			out.println("<h1>Hello Servlet WTF</h1>");
			out.println("</body>");
			out.println("</html>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void messageLogged(BuildEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void targetFinished(BuildEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void targetStarted(BuildEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void taskFinished(BuildEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void taskStarted(BuildEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
