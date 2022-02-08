package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;

@Service
public class BlogService {

	@Autowired
	BlogDao blogDao;
	
	public BlogVo getBlog(String id) {
		System.out.println("[BlogService.getBlog]");
		return blogDao.getBlog(id);
	}
}
