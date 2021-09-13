package facebookweb;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.facebookweb.dao.FacebookDAOInterface;
import com.facebookweb.entity.FacebookUser;
import com.facebookweb.utility.DAOFactory;

public class FacebookDAOHibernateTest
{
	FacebookDAOInterface fd1;

	@Before
	public void setUp() throws Exception 
	{
		fd1 = DAOFactory.createObjectHibernate();
	}
	
	@After
	public void tearDown() throws Exception {
		fd1=null;
	}

	@Test
	public void testCreateProfileDAO() 
	{
		 FacebookUser fu = new FacebookUser(); 
		 fu.setName("Arun");
		 fu.setPassword("456");
		 fu.setEmail("arun@gmail.com");
		 fu.setAddress("hydrabad");
		
		int i=fd1.createProfileDAO(fu);
		assert i>0:"Registration failed";
	}

	@Test
	public void testLoginProfile()
	{	
	/*	FacebookUser fu = new FacebookUser();
		fu.setEmail("arun@gmail.com");
		fu.setPassword("456");
		boolean b = fd1.loginProfile(fu);
		assert b : "Login Failed"; */
	}

	@Test
	public void testViewProfile()
	{	
	/*	FacebookUser fu = new FacebookUser();
		fu.setEmail("arun@gmail.com");
		FacebookUser f=fd1.viewProfile(fu);
		assert f != null : "Failed to view profile"; */
	}
	
	@Test
	public void testSearchProfile()
	{	/*
		FacebookUser fu = new FacebookUser();
		fu.setName("Arun");
		FacebookUser f=fd1.searchProfile(fu);
		assert f != null : "Profile not found"; */
	}

	@Test
	public void testEditProfile() 
	{/*
		FacebookUser fu = new FacebookUser(); 
		fu.setName("Arun1");
		fu.setPassword("123");
		fu.setEmail("anil@gmail.com");
		fu.setAddress("Mumbai");
		
		int i=fd1.editProfile(fu);
		assert i > 0 : "Failed to edit profile";
		//fail("Not yet implemented");
		 */
	}

	@Test
	public void testDeleteProfile()
	{/*
		FacebookUser fu = new FacebookUser();
		fu.setEmail("arun@gmail.com");
		fu.setPassword("123");
		int i = fd1.deleteProfile(fu);
		assert i>0 : "Delete failed";
		*/
	}

}
