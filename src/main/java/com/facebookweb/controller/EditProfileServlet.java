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


public class EditProfileServlet extends HttpServlet
{
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession ss=request.getSession(true);
		Object oo=ss.getAttribute("id");
		
		FacebookUser fu=new FacebookUser();
		fu.setName(oo.toString());
				
		FacebookDAOInterface fd=DAOFactory.createObjectHibernate();
		
		FacebookUser b=fd.viewProfile(fu);
		/*response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<html><body><center><br><br>");
		if(b!=null)
		{
				out.println("<p>Edit Profile Page</p><br>");
				out.println("<form method=post action=EditProfileServlet>");
				out.println("Name : <input type=text name=nm value="+b.getName()+" disabled>");
				out.println("<br>Password : <input type=text name=pw value="+b.getPassword()+">");
				out.println("<br>Email :<input type=email name=em value="+b.getEmail()+">");
				out.println("<br>Address :<input type=text name=ad value="+b.getAddress()+">");
				out.println("<input type=submit value=Edit>");
				out.println("</form>");
		}
		else
		{
				out.println("profile not found");
		}
			*/
		if(b!=null)
		{	System.out.println("from");
			String name =request.getParameter("nm"); 
			String password = request.getParameter("pw");
			//String email = request.getParameter("em"); 
			String address =request.getParameter("ad");
			
			fu.setName(name);
			fu.setPassword(password);
			//fu.setEmail(b.getEmail());
			//System.out.println(fu.getEmail());
			fu.setEmail(oo.toString());
			fu.setAddress(address);
			int i =fd.editProfile(fu);
			
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			out.println("<html><body><center><br><br>");

			if(i>0)
			{
				out.println("<font size=5 color=green><b>Profile Edited Successfully</font>");
			}
			else
			{
				out.println("your password or email is wrong..");
			
			}
			
		out.println("</center></body></html>");
		}
	}
}
