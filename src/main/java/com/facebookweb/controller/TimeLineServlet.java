package com.facebookweb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.facebookweb.dao.FacebookDAOInterface;
import com.facebookweb.entity.FacebookUser;
import com.facebookweb.entity.TimeLine;
import com.facebookweb.utility.DAOFactory;

public class TimeLineServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ss=request.getSession(true);
		Object oo=ss.getAttribute("id");
		
		FacebookUser fu=new FacebookUser();
		fu.setName(oo.toString());
				
		FacebookDAOInterface fd=DAOFactory.createObject();
		List ll=fd.getTimeLine(fu);
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<html><body><center><br><br>");
		
		for(Object oo1:ll) {
			out.println("****************************************");
			if(oo1 instanceof FacebookUser) {
				FacebookUser ff=(FacebookUser)oo1;
				out.println("<br>message for "+ff.getEmail());
			}
			if(oo1 instanceof TimeLine) {
				TimeLine ff=(TimeLine)oo1;
				out.println("<br>sender is "+ff.getSender());
				out.println("<br>"+ff.getMessage());
			}
		}
		out.println("</center></body></html>");
		
		
	}

}
