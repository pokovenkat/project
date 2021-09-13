package com.facebookweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Successlogin extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext sc=getServletContext();
		Object oo=sc.getAttribute("id");
		
		HttpSession ss=request.getSession(true);
		Object oo1=ss.getAttribute("pwd");
		
		//Cookie c[]=request.getCookies();
		
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<html><body><center><br><br>");
		
		out.println("<font size=5 color=blue><b>Welcome from successlogin page "+oo+" and password is "+oo1+"<a href=ViewProfileServlet> view profile</a>   <a href=SearchProfileServlet>Search profile</a><a href=EditProfileServlet> Edit profile</a><a href=TimeLineServlet>timeline</a> </b></font>");

		/*
		 * for(int i=0;i<c.length;i++) { Cookie c1=c[i];
		 * out.println("<br><br><br>cookie name "+c1.getName());
		 * out.println("cookie value"+c1.getValue()); }
		 */
		
		out.println("</center></body></html>");
	}

}
