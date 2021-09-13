package com.facebookweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.facebookweb.dao.FacebookDAOInterface;
import com.facebookweb.entity.FacebookUser;
import com.facebookweb.utility.DAOFactory;

public class LoginServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("em");
		String password=request.getParameter("pw");
		
		FacebookUser fu=new FacebookUser();
		fu.setEmail(email);
		fu.setPassword(password);
		
		FacebookDAOInterface fd=DAOFactory.createObjectHibernate();
		
		boolean b=fd.loginProfile(fu);
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<html><body><center><br><br>");
		if(b) {
			//how one servlet will share attributes with other servlet?
			//via container
			//how?
			//values will be on container untill container will not destroy i.e. server not started
			ServletContext sc=getServletContext();
			sc.setAttribute("id", email);
			
			//if we want to share information for limited time then we should use session concept
			//how?
			
			
			HttpSession ss=request.getSession(true);
			ss.setAttribute("id", email);
			ss.setAttribute("pwd", password);
			
		//	String s=request.getRequestURI();
			//System.out.println(s);
			
		//	response.encodeUrl("/facebookweb/LoginServlet?sid="+ss.getId());
			//by default session will be valid for 30 minutes
			//if we want to set timing  then how?
			
			//ss.setMaxInactiveInterval(5);
			//here session information will destroy in 5 minutes
			
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/Successlogin");
			rd.forward(request, response);
				}
		else {
			out.println("<font size=5 color=red><b>Invalid id and password </b></font>");
			//if we want to call other servlet, html or jsp pages from servlet then we should use RequestDispathcher concept
			//how to implement?
			//why servletContext? to access servlet container.
			//how many ServletContext can be in one project? only one ServletContext.
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/login.html");
			rd.include(request, response);
		}
		out.println("</center></body></html>");
	}

}







