package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {

	@Autowired
	SqlSession sqlSession;
	
	//블로그 정보 가져오기
	public BlogVo getBlog(String id) {
		System.out.println("[BlogDao.getBlog]");
		
		BlogVo blogVo = sqlSession.selectOne("blog.getBlog", id);
			
		return blogVo;
	}
	
	public void setBlog(BlogVo blogVo) {
		System.out.println("[BlogDao.blogVo]");
		int count = sqlSession.insert("blog.setBlog",blogVo);
		System.out.println("["+count+"건이 생성되었습니다.(blog)]");
	}
}
