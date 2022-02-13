package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	UserDao userDao;
	@Autowired
	BlogDao blogDao;
	@Autowired
	CategoryDao categoryDao;

	// 사용자 회원가입
	public void join(UserVo userVo) {
		System.out.println("[UserService.join]");

		userDao.userInsert(userVo);

		// 블로그 생성
		BlogVo blogVo = new BlogVo();
		blogVo.setId(userVo.getId());
		blogVo.setLogoFile("spring-logo.jpg");
		blogDao.setBlog(blogVo);

		// 블로그 카테고리 생성
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setId(userVo.getId());
		categoryVo.setCateName("미분류");
		categoryVo.setDescription("기본으로 만들어지는 카테고리 입니다.");
	}

	public String idCheck(UserVo userVo) {
		System.out.println("[UserService.idCheck]");

		String resultId = userDao.getId(userVo);

		if (resultId == null) {
			return "success";
		} else {
			return "fail";
		}
	}

	public UserVo login(UserVo userVo) {
		System.out.println("[UserService.login]");
		return userDao.getUser(userVo);
	}
}
