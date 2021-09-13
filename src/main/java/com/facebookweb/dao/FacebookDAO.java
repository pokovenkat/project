package com.facebookweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.facebookweb.entity.FacebookUser;
import com.facebookweb.entity.TimeLine;

public class FacebookDAO implements FacebookDAOInterface {
	private Connection con;
	public FacebookDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/webproject","root","Subhashnayak@1999s");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public int createProfileDAO(FacebookUser fu) {
		int i=0;
		try {
			PreparedStatement ps=con.prepareStatement("insert into facebookuser values(?,?,?,?)");
			ps.setString(1, fu.getName());
			ps.setString(2, fu.getPassword());
			ps.setString(3, fu.getEmail());
			ps.setString(4, fu.getAddress());
			
			i=ps.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public boolean loginProfile(FacebookUser fu) {
		boolean b=false;
		try {
			PreparedStatement ps=con.prepareStatement("select * from facebookuser where email=? and password=?");
			ps.setString(1, fu.getEmail());
			ps.setString(2, fu.getPassword());
			
			ResultSet res=ps.executeQuery();
			if(res.next()) {
				b=true;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	public FacebookUser viewProfile(FacebookUser fu) {
		FacebookUser ff=null;
		try {
			PreparedStatement ps=con.prepareStatement("select * from facebookuser where email=?");
			ps.setString(1, fu.getEmail());
			
			
			ResultSet res=ps.executeQuery();
			if(res.next()) {
				ff=new FacebookUser();
				ff.setName(res.getString(1));
				ff.setPassword(res.getString(2));
				ff.setEmail(res.getString(3));
				ff.setAddress(res.getString(4));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return ff;
	}

	public List getTimeLine(FacebookUser fu) {
		List ll=new ArrayList();
		try {
			PreparedStatement ps=con.prepareStatement("select t.senderid,t.tmessage,f.email,f.name,f.address from facebookuser f, timeline t where f.email=t.email and f.email=?");
			ps.setString(1, fu.getName());
			
			
			ResultSet res=ps.executeQuery();
			while(res.next()) {
				TimeLine t=new TimeLine();
				t.setSender(res.getString(1));
				t.setMessage(res.getString(2));
				
				FacebookUser ff=new FacebookUser();
				ff.setEmail(res.getString(3));
				ff.setName(res.getString(4));
				ff.setAddress(res.getString(5));
				
				ll.add(t);
				ll.add(ff);
				
			}
			System.out.println(ll.size());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return ll;
	}

	public int checkEmailDAO(FacebookUser fu) {
		int i=0;
		try {
			PreparedStatement ps=con.prepareStatement("select * from facebookuser where email=?");
			ps.setString(1, fu.getEmail());
			
			
			ResultSet res=ps.executeQuery();
			if(res.next()) {
				
				i=1;
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public List<FacebookUser> allUser() {
		// TODO Auto-generated method stub
		return null;
	}

	public int editProfile(FacebookUser ff) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteProfile(FacebookUser fu) {
		// TODO Auto-generated method stub
		return 0;
	}

	public FacebookUser searchProfile(FacebookUser fu) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<FacebookUser> allUser(FacebookUser fu) {
		// TODO Auto-generated method stub
		return null;
	}

}











