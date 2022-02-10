package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Repository
public class PostDao {

	@Autowired
	SqlSession sqlSession;
	
	//카테고리의 전체 포스트 가져오기
	public List<PostVo> getPostList(int cateNo){
		System.out.println("[PostDao.getMainList]");
		
		return sqlSession.selectList("post.getPostList", cateNo);
	}
	
	//카테고리의 최상단 글 띄우기
	public PostVo getPostTop(int cateNo){
		System.out.println("[PostDao.getPostTop]");
		
		return sqlSession.selectOne("post.getPostTop", cateNo);
	}
	
	//카테고리 추가
	public int insertPost(PostVo postVo) {
		System.out.println("[PostDao.insertPost]");
		System.out.println(postVo);
		int count = sqlSession.insert("post.insertPost", postVo);
		
		return count;
	}
	
	//카테고리 글 갯수 가져오기
	public int postCnt(int cateNo) {
		System.out.println("[PostDao.postCnt]");
		
		return sqlSession.insert("post.postCnt", cateNo);
	}
}
