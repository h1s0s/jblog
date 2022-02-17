package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	SqlSession sqlSession;

	// 회원가입
	public int userInsert(UserVo userVo) {
		System.out.println("[UserDao.userInsert]");

		int count = sqlSession.insert("user.insert", userVo);
		System.out.println("[" + count + "건이 등록되었습니다.(User)]");

		return count;
	}

	// 아이디 중복체크
	public String getId(UserVo userVo) {
		System.out.println("[UserDao.getId]");

		return sqlSession.selectOne("user.getId", userVo);
	}

	// 로그인 확인
	public UserVo getUser(UserVo userVo) {
		System.out.println("[UserDao.getUser]");

		return sqlSession.selectOne("user.getUser", userVo);
	}
}
