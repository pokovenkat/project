package com.facebookweb.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.facebookweb.entity.FacebookUser;

public class FacebookDAOHibernate implements FacebookDAOInterface 
{
	private SessionFactory sf;
	
	public FacebookDAOHibernate() 
	{
		sf=new Configuration().configure().buildSessionFactory(); //configure method will search for hibernate.cfg.xml and load, based on that it will create database connection,table
	
	}

	public int createProfileDAO(FacebookUser fu)
	{
		int i=0;
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		s.save(fu);
		t.commit();
		i=1;
		return i;
	}

	public boolean loginProfile(FacebookUser fu) 
	{
		boolean b=false;
		Session s=sf.openSession();
		//Query q=s.createQuery("from FacebookUser fu where fu.email='"+fu.getEmail()+"' and fu.password='"+fu.getPassword()+"'");
		Query q=s.createNamedQuery("logindata");
		q.setParameter("email", fu.getEmail());
		q.setParameter("pass", fu.getPassword());
		List<FacebookUser> ff=q.getResultList();
		if(ff.size()>0) 
		{
			b=true;
		}
		return b;
	}

	public FacebookUser viewProfile(FacebookUser fu) 
	{
		
		System.out.println(fu.getEmail());
		Session s=sf.openSession();
		Query q=s.createQuery("from FacebookUser fu where fu.email='"+fu.getEmail()+"'");
		//Query q1=s.createCriteria("FacebookUser");
		FacebookUser ff=(FacebookUser)q.getSingleResult();
		//FacebookUser ff=s.load(FacebookUser.class, fu.getEmail());
		return ff;
	}
	
	public int editProfile(FacebookUser fu) 
	{
		System.out.println("Hello i am in hibernate");
		int i=0;
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		s.update(fu);  //no 1 but not used in real
	//	Query q=s.createQuery("update FacebookUser fu set fu.name="+fu.getName()+" where fu.email="+fu.getEmail()).setCacheable(true); //no 2 and used in real
		///i=q.executeUpdate();
		t.commit();
		i=1;
	//	FacebookUser ff=s.load(FacebookUser.class, fu.getEmail());
		return i;
	}
	
	public int deleteProfile(FacebookUser fu) 
	{
		int i=0;
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		s.delete(fu);
		//Query q=s.createQuery("delete from FacebookUser fu where fu.email="+fu.getEmail()); //no 2 and used in real
		//i=q.executeUpdate();
		t.commit();
		i=1;
		return i;
	}
	
/*	public List<FacebookUser> allUser() 
	{
		Session s=sf.openSession();
		Query q=s.createQuery("from FacebookUser fu");
		return q.getResultList();
	}*/

	public FacebookUser searchProfile(FacebookUser fu) 
	{	
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
			
		Query q = s.createQuery("from FacebookUser fu where fu.name='" + fu.getName() + "'");
		FacebookUser ff = (FacebookUser) q.getSingleResult();
		return ff;
	}
	
	
	public List<FacebookUser> allUser(FacebookUser fu) 
	{
		Session s=sf.openSession();
		Query q=s.createQuery("from FacebookUser fu");
		List<FacebookUser> ff=(List<FacebookUser>)q.getResultList();
	//	return q.getResultList();
		return ff;
	}
	
	
	
	
	
	
	
	
	
	
	
	

	public List getTimeLine(FacebookUser fu) {
		// TODO Auto-generated method stub
		return null;
	}

	public int checkEmailDAO(FacebookUser fu) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
