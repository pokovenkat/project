package com.facebookweb.dao;

import java.util.List;

import com.facebookweb.entity.FacebookUser;

public interface FacebookDAOInterface {

	int createProfileDAO(FacebookUser fu);

	boolean loginProfile(FacebookUser fu);

	FacebookUser viewProfile(FacebookUser fu);

	//List<FacebookUser> allUser();

	int editProfile(FacebookUser fu);

	int deleteProfile(FacebookUser fu);

	FacebookUser searchProfile(FacebookUser fu);

	List getTimeLine(FacebookUser fu);

	int checkEmailDAO(FacebookUser fu);

	List<FacebookUser> allUser(FacebookUser fu);


}
