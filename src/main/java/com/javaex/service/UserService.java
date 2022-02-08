package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
	@Autowired
	BlogDao blogDao;
	
	public void join(UserVo userVo) {
		System.out.println("[UserService.join]");
		
		userDao.userInsert(userVo);
		
		//블로그 생성
		BlogVo blogVo = new BlogVo();
		blogVo.setId(userVo.getId());
		blogVo.setBlogTitle(userVo.getUserName()+"의 블로그입니다.");
		blogVo.setLogoFile("spring-logo.jpg");
		blogDao.setBlog(blogVo);
	}
	
	public UserVo login(UserVo userVo) {
		System.out.println("[UserService.login]");
		return userDao.getUser(userVo);
	}
}
