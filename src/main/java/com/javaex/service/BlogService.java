package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Service
public class BlogService {

	@Autowired
	BlogDao blogDao;
	@Autowired
	CategoryDao categoryDao;
	@Autowired
	PostDao postDao;
	
	//블로그 메인
	public Map<String, Object> getBlog(String id) {
		System.out.println("[BlogService.getBlog]");
		Map<String, Object> bMap = new HashMap<String, Object>();
		
		//블로그
		BlogVo blogVo = blogDao.getBlog(id);
		bMap.put("blogVo", blogVo);
		
		//카테고리
		List<CategoryVo> categoryList = categoryDao.getCategory(id);
		bMap.put("categoryList", categoryList);
		
		//전체 포스트(메인화면용, 카테고리 상관 없이)
		List<PostVo> postList = postDao.getMainList(id);
		bMap.put("postList", postList);
		
		//가장 최신 포스트(메인화면용, 카테고리 상관 없이)
		PostVo postVo = postDao.getMainOne(id);
		bMap.put("postVo", postVo);
		
		return bMap;
	}
	
	//블로그 헤더 정보
	public BlogVo getHeader(String id) {
		System.out.println("[BlogService.getHeader]");
		return blogDao.getBlog(id);
	}
	
	//블로그 정보 수정
	public void updateBlog(BlogVo blogVo, MultipartFile file) {
		System.out.println("[BlogService.updateBlog]");
		String saveDir = "/Users/hs/JavaStudy/upload/";
		
		// 원본파일이름
		String orgName = file.getOriginalFilename();
		
		// 확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		
		// 저장파일이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
		
		// 파일패스 생성
		String filePath = saveDir + saveName;
		
		// 파일 사이즈
		long fileSize = file.getSize();// long
		
		// 파일 저장
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);// 어떤 경로에 파일을 저장할건지?
			BufferedOutputStream bout = new BufferedOutputStream(out);

			bout.write(fileData);
			bout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		blogVo.setLogoFile(saveName);
		
		blogDao.update(blogVo);
	}
	
	//카테고리 관리 페이지
	public List<CategoryVo> getCategory(String id) {
		System.out.println("[BlogService.getCategory]");
		
		//카테고리
		List<CategoryVo> categoryList = categoryDao.getCategory(id);
		
		return categoryList;
	}
	
	//카테고리 등록
	public int addCategory(CategoryVo categoryVo) {
		System.out.println("[BlogService.addCategory]");
		
		return categoryDao.addCategory(categoryVo);
	}
	
	//글작성 페이지
	public void setPost(PostVo postVo) {
		System.out.println("[PostService.setPost]");
		postDao.insertPost(postVo);
	}
}
