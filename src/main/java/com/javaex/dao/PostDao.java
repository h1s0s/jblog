package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PostVo;

@Repository
public class PostDao {

	@Autowired
	SqlSession sqlSession;
	
	public List<PostVo> getMainList(String id){
		System.out.println("[PostDao.getMainList]");
		
		return sqlSession.selectList("post.getMainList", id);
	}
	
	public PostVo getMainOne(String id){
		System.out.println("[PostDao.getMainList]");
		
		return sqlSession.selectOne("post.getMainOne", id);
	}
	
	public int insertPost(PostVo postVo) {
		System.out.println("[PostDao.insertPost]");
		
		int count = sqlSession.insert("post.insertPost", postVo);
		
		return count;
	}
	
	public int postCnt(int cateNo) {
		System.out.println("[PostDao.postCnt]");
		
		return sqlSession.insert("post.postCnt", cateNo);
	}
}
