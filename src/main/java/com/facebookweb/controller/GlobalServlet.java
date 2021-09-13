package com.facebookweb.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.facebookweb.dao.FacebookDAOInterface;
import com.facebookweb.entity.FacebookUser;
import com.facebookweb.utility.DAOFactory;
import com.fasterxml.jackson.databind.*;


public class GlobalServlet extends HttpServlet
{
	private static Logger log = Logger.getLogger(GlobalServlet.class);
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String ss=request.getParameter("choice");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><boy><center><br><br>");
	
		out.println("<style type=text/css>"
				+ "*{"
				+ "margin:0;"
				+ "padding: 0;"
				+ "}"
				+ "body{"
				+ "background: url(images/r.jpg);"
						+ "background-size: 100%;"
						+ "}");
		out.println("</style>");
		
		if(ss.equals("register"))
		{
			 	log.info("Entering into register file");
				String name =request.getParameter("nm"); 
				String password = request.getParameter("pw");
				String email = request.getParameter("em"); 
				String address =request.getParameter("ad");
					  
				FacebookUser fu = new FacebookUser(); 
				fu.setName(name);
				fu.setPassword(password);
				fu.setEmail(email);
				fu.setAddress(address);
					  
				FacebookDAOInterface fd1 = DAOFactory.createObjectHibernate();
					  	  
				int i = fd1.createProfileDAO(fu);
				log.info("Getting value of i from dao "+i);
				
				if (i > 0) 
				{ 
					  out.println("<font size=5 color=blue><b>Registration success <a href=login.html>click here</a> to continue</b></font>");
					  log.info("Success register");
				} 
				else 
				{ 
						out.println("<font size=5 color=red><b>Registration Fail try again</b></font>");
				}
		}
		else if(ss.equals("login"))
		{		
				log.info("Entering into login file");
				String email=request.getParameter("em");
				String password=request.getParameter("pw");
			
				FacebookUser fu=new FacebookUser();
				fu.setEmail(email);
				fu.setPassword(password);
			
				FacebookDAOInterface fd=DAOFactory.createObjectHibernate();
			
				boolean b=fd.loginProfile(fu);
				
				if(b) 
				{
					ServletContext sc=getServletContext();
					sc.setAttribute("em", email);
				
					HttpSession ss1=request.getSession(true);
					ss1.setAttribute("em", email);
					ss1.setAttribute("pw", password);
			
					RequestDispatcher rd=getServletContext().getRequestDispatcher("/GlobalServlet?choice=success");
					rd.forward(request, response);
				
				}
				else 
				{
					out.println("<font size=5 color=red><b>Invalid id and password </b></font>");
				
					RequestDispatcher rd=getServletContext().getRequestDispatcher("/login.html");
					rd.include(request, response);
				}
		}
		else if(ss.equals("success"))
		{
			log.info("Entering into success file");
			ServletContext sc=getServletContext();
			Object oo=sc.getAttribute("em");
			
			HttpSession ss1=request.getSession(true);
			Object oo1=ss1.getAttribute("pw");
			out.println("<font size=3 color=White>");
			out.println("<a href=index.html> Log Out</a><br><br>");
			out.println("</font>");
			out.println("<font size=5 color=White><b><h1>.....Welcome To Aria.....<h1></b></font>");
			out.println("<nav>"
					+ "<a href=GlobalServlet?choice=viewProfile>View Your Profile</a><br>"
					+ "<a href=GlobalServlet?choice=edit>Edit profile</a><br>"
					+ "<a href=GlobalServlet?choice=search>Search Profile</a><br>"
					+ "<a href=GlobalServlet?choice=viewAll>View All Profiles</a><br>"
					+ "<a href=GlobalServlet?choice=delete>Delete Profile</a><br>"
					+ "</nav>");
			
		}
		else if(ss.equals("viewProfile"))
		{
			log.info("Entering into view file");
			HttpSession ss1=request.getSession(true);
			Object oo1=ss1.getAttribute("em");
	
			FacebookUser fu=new FacebookUser();
			fu.setEmail(oo1.toString());
					
			FacebookDAOInterface fd=DAOFactory.createObjectHibernate();
			
			FacebookUser b=fd.viewProfile(fu);
			out.println("<font size=7 color=pink><b>*******Your Profile*******</font> <br><br>");
			
			if(b!=null) 
			{	
				out.println("<font size=5 color=Maroon><b>Name &nbsp&nbsp &nbsp&nbsp    :"+b.getName()+"<br></b></font>");
				out.println("<font size=5 color=Maroon><b><br>Password &nbsp&nbsp &nbsp&nbsp:"+b.getPassword()+"<br></b></font>");
				out.println("<font size=5 color=Maroon><b><br>Email    :"+b.getEmail()+"<br></b></font>");
				out.println("<font size=5 color=Maroon><b><br>Address &nbsp&nbsp &nbsp&nbsp  :"+b.getAddress()+"<br></b></font>");
				
				log.info("View profile");
			}
			else 
			{
					out.println("profile not found");
			}
			out.println("<font size=5 color=Crimson><b><br><b><a style=color:DarkOrange; href=GlobalServlet?choice=success>Go to home</a> </b></font>");
	
		}
		else if(ss.equals("edit"))
		{
			log.info("Entering into edit file");
			HttpSession ss1=request.getSession(true);
			Object oo=ss1.getAttribute("em");
			
			FacebookUser fb=new FacebookUser();
			fb.setEmail(oo.toString());
					
			FacebookDAOInterface fd=DAOFactory.createObjectHibernate();
			
			FacebookUser b=fd.viewProfile(fb);
			FacebookUser fu = new FacebookUser();
		
			if(b!=null) 
			{
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/edit.html?email=" + oo.toString() + "");
				rd.include(request, response);
				/*out.println("<p>Edit Profile Page</p><br>");
					out.println("<form method=post>");
					out.println("<br>Name     : <input type=text name=nm value="+b.getName()+" ><br>");
					out.println("<br>Password :<input type=text name=pw value="+b.getPassword()+"><br>");
					out.println("<br>Email    :<input type=email name=em value="+b.getEmail()+" disabled><br>");
					out.println("<br>Address  :<input type=text name=ad value="+b.getAddress()+"><br>");
					out.println("<br><input type=submit value=Edit>");
					out.println("</form>");
				*/
			}
			else
			{
					out.println("<font size=5 color=red><b>Profile Not Found</font>");
			}	
			out.println("</center></body></html>");
			
			if(b!=null)
			{	
				
				String name =request.getParameter("nm"); 
				String password = request.getParameter("pw");
				//String email = request.getParameter("em"); 
				String address =request.getParameter("ad");
				
				fu.setName(name);
				fu.setPassword(password);
				fu.setEmail(oo.toString());
				fu.setAddress(address);
				int i =fd.editProfile(fu);
				response.setContentType("text/html");
			
				out.println("<html><body><center><br><br>");

				if(i>0)
				{
					//out.println("<font size=5 color=green><b>Profile Edited Successfully</font>");
					log.info("Success edit");
				}
				else
				{
					out.println("your password or email is wrong..");
				
				}
			
			}
		}
		
		else if(ss.equals("viewAll"))
		{
			log.info("Entering into view file");
			//response.setContentType("application/json");
			//esponse.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
			FacebookUser fu = new FacebookUser();
			FacebookDAOInterface fd1 = DAOFactory.createObjectHibernate();//hibernate
			List<FacebookUser> ff=fd1.allUser(fu);
			out.println("<font size=7 color=pink><b>*******All Users*******</font> <br><br>");
			for(FacebookUser b:ff)
			{
				out.println("**************************************<br>");
				out.println("<font size=5 color=Maroon><b>Name &nbsp&nbsp &nbsp&nbsp    :"+b.getName()+"</b></font>");
			//	out.println("<font size=5 color=Maroon><b><br>Password &nbsp&nbsp &nbsp&nbsp:"+b.getPassword()+"<br></font>");
				out.println("<font size=5 color=Maroon><b><br>Email    :"+b.getEmail()+"</b></font>");
				out.println("<font size=5 color=Maroon><b><br>Address &nbsp&nbsp &nbsp&nbsp  :"+b.getAddress()+"</b></font>");
				
				out.println("<br>**************************************<br>");
			}
			
			out.println("<font size=5 color=Crimson><b><br><b><a style=color:DarkOrange; href=GlobalServlet?choice=success>Go to home</a> </b></font>");
			/*
			String json =null;
			ObjectMapper objectMapper = new ObjectMapper();
		    try 
		    {
		        json= objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(ff);
		         System.out.println(json);
		    }
		    catch(Exception e) 
		    {
		         e.printStackTrace();
		     }
		      
			out.println(json);
			*/
			
		}
		else if(ss.equals("delete"))
		{	
			log.info("Entering into delete file");
			HttpSession ss1=request.getSession(true);
			Object oo1=ss1.getAttribute("em");
	
			FacebookUser fu=new FacebookUser();
			FacebookDAOInterface fd=DAOFactory.createObjectHibernate();
			fu.setEmail(oo1.toString());
			out.println("<font size=7 color=pink><b>*******Delete Your Profile*******</font> <br><br>");
			out.println("<form method=post>  ");
			out.println("<font size=6 color=Maroon><b>Email  &nbsp&nbsp &nbsp&nbsp  : <input type=email name=em placeholder='Enter email'><br><br><br></b></font>");
			out.println("<font size=6 color=Maroon><b>Password : <input type=password name=pw placeholder='Enter password'><br><br></b></font>");
			out.println("<input type=submit value=Delete  >");
			out.println("</form>  ");
			
			out.println("</center></body></html>");
			
			String email = request.getParameter("em");
			String password =request.getParameter("pw");
			//System.out.println(email);
			fu.setEmail(email);
			fu.setPassword(password);
			int i = fd.deleteProfile(fu);
			log.info("Getting value of i from dao "+i);
			
			out.println("<html><body><center><br><br>");
			if(i>0)
			{	
				//out.println("<font=5 color=orange><b>Profile Deleted Succesfully.</b></font>");
				out.println("<br><br><h2><a style=color:white; href=index.html>Click here to continue...</a></h2>");
				log.info("Success delete");
			}
			else
			{
				out.println("Something went wrong, please try again");
			}
		}
		
		else if(ss.equals("search"))
		{		
			int i=1;
			FacebookDAOInterface fd=DAOFactory.createObjectHibernate();
			FacebookUser fu=new FacebookUser();
			if(i>0)
			{		
				out.println(" <font size=5 color=pink><b>Enter Name to search Profile</font> <br><br>");
				out.println("<form method=post>");
				out.println("<font size=5 color=Green>Name : <input type=text name=nm placeholder='Enter the Name'></font> <br><br>");
				out.println("<input type=submit value=Search  >");
				out.println("</form>  ");
			}
			
			String name = request.getParameter("nm");
			System.out.println(name);
			fu.setName(name);
			FacebookUser b = fd.searchProfile(fu);
			if(b!=null)
			{
				out.println("<font size=4 color=Maroon><b><br><br>User Found <br><br>Name &nbsp&nbsp:-"+b.getName()+"<br></b></font>");
				out.println("<font size=4 color=Maroon><b>Email &nbsp&nbsp:-"+b.getEmail()+"<br></b></font>");
				out.println("<font size=4 color=Maroon><b>Address :-"+b.getAddress()+"<br></b></font>");
				log.info("Search success");
			}
			else
			{
				out.println("Profile not found");
			}
			out.println("<font size=5 color=Crimson><b><br><b><a style=color:DarkOrange; href=GlobalServlet?choice=success>Go to home</a> </b></font>");
		}
		out.println("</center></body></html>");	
	}

}
