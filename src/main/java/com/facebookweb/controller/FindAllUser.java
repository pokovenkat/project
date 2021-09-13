package com.facebookweb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.facebookweb.dao.FacebookDAOInterface;
import com.facebookweb.entity.FacebookUser;
import com.facebookweb.utility.DAOFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FindAllUser extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		
		PrintWriter out=response.getWriter();
		
		FacebookDAOInterface fd1 = DAOFactory.createObjectHibernate();//hibernate
		List<FacebookUser> ff=fd1.allUser();
		
		 String json =null;
		ObjectMapper objectMapper = new ObjectMapper();
	      try {
	        json= objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(ff);
	         System.out.println(json);
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	      
		out.println(json);
	}

}
