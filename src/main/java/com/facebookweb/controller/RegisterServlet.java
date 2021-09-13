package com.facebookweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.facebookweb.dao.FacebookDAOInterface;
import com.facebookweb.entity.FacebookUser;
import com.facebookweb.utility.DAOFactory;

public class RegisterServlet extends HttpServlet {
	
	 private static Logger log = Logger.getLogger(RegisterServlet.class);
	
	  protected void service(HttpServletRequest request, HttpServletResponse
	  response) throws ServletException, IOException { 
		  
		  log.info("entering into RegisterServlet file");
		  
		  String name =
	  request.getParameter("nm"); String password = request.getParameter("pw");
	  String email = request.getParameter("em"); String address =
	  request.getParameter("ad");
	  
	  FacebookUser fu = new FacebookUser(); 
	  fu.setName(name);
	  fu.setPassword(password);
	  fu.setEmail(email);
	  fu.setAddress(address);
	  
	  FacebookDAOInterface fd = DAOFactory.createObject();  //jdbc
	  FacebookDAOInterface fd1 = DAOFactory.createObjectHibernate();//hibernate
	  
	  
	  int i = fd1.createProfileDAO(fu);
	  
	  log.info("getting value of i from dao "+i);
	  
	  response.setContentType("text/html"); PrintWriter out = response.getWriter();
	  out.println("<html><body><center><br><br>"); if (i > 0) { out.println(
	  "<font size=5 color=blue><b>Registration success <a href=login.html>click here</a> to continue</b></font>"
	  ); } else { out.
	  println("<font size=5 color=red><b>Registration Fail try again</b></font>");
	  } out.println("</center></body></html>");
	  
	  }
	 
		/*
		 * protected void doPost(HttpServletRequest request, HttpServletResponse
		 * response) throws ServletException, IOException {
		 * response.setContentType("text/html"); PrintWriter out = response.getWriter();
		 * out.println("i am post method"); }
		 * 
		 * protected void doGet(HttpServletRequest request, HttpServletResponse
		 * response) throws ServletException, IOException {
		 * response.setContentType("text/html"); PrintWriter out = response.getWriter();
		 * out.println("i am get method"); }
		 * 
		 * protected void doPut(HttpServletRequest request, HttpServletResponse
		 * response) throws ServletException, IOException {
		 * response.setContentType("text/html"); PrintWriter out = response.getWriter();
		 * out.println("i am put method"); }
		 * 
		 * protected void doDelete(HttpServletRequest request, HttpServletResponse
		 * response) throws ServletException, IOException {
		 * response.setContentType("text/html"); PrintWriter out = response.getWriter();
		 * out.println("i am delete method"); }
		 */

}
