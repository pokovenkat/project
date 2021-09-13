package com.facebookweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.facebookweb.dao.FacebookDAOInterface;
import com.facebookweb.entity.FacebookUser;
import com.facebookweb.utility.DAOFactory;

/**
 * Servlet implementation class CheckEmail
 */
public class CheckEmail extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		FacebookUser fu=new FacebookUser();
		fu.setEmail(email);
		FacebookDAOInterface fd = DAOFactory.createObject();
		  
		  int i = fd.checkEmailDAO(fu);
		  response.setContentType("text/html"); PrintWriter out = response.getWriter();
		  out.println("<html><body><center><br><br>");
		  if(i>0) {
			  out.println("email already exist");
		  }
		  else {
			  out.println("valid mail");
		  }
		  out.println("</center></body></html>");
	}

}
