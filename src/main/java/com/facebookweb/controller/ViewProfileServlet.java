package com.facebookweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.facebookweb.dao.FacebookDAOInterface;
import com.facebookweb.entity.FacebookUser;
import com.facebookweb.utility.DAOFactory;

public class ViewProfileServlet extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ss=request.getSession(true);
		Object oo1=ss.getAttribute("id");
		
		FacebookUser fu=new FacebookUser();
		fu.setEmail(oo1.toString());
				
		FacebookDAOInterface fd=DAOFactory.createObjectHibernate();
		
		FacebookUser b=fd.viewProfile(fu);
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<html><body><center><br><br>");
			if(b!=null) {
				out.println("Name  -->"+b.getName());
				out.println("<br>Password is -->"+b.getPassword());
				out.println("<br>Email is -->"+b.getEmail());
				out.println("<br>Address is -->"+b.getAddress());
			}
			else {
				out.println("profile not found");
			}
		out.println("</center></body></html>");
	}

}







