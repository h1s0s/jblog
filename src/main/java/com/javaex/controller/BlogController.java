package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Controller
public class BlogController {

	@Autowired
	BlogService blogService;

	// 블로그 메인
	@RequestMapping("/{id}")
	public String blog(@PathVariable("id") String id, Model model) {
		System.out.println("[BlogController.blog]");
		Map<String, Object> bMap = blogService.getBlog(id);
		BlogVo authVo = (BlogVo)bMap.get("blogVo");
		
		if (authVo != null) {
			System.out.println("[블로그 접속 성공]");
			
			List<CategoryVo> categoryList = (List<CategoryVo>)bMap.get("categoryList");
			List<PostVo> postList = (List<PostVo>) bMap.get("postList");
			BlogVo blogVo = (BlogVo)bMap.get("blogVo");
			PostVo postVo= (PostVo) bMap.get("postVo");
			
			System.out.println(blogVo);
			model.addAttribute("blogVo", blogVo);
			model.addAttribute("categoryList", categoryList);
			model.addAttribute("postList", postList);
			model.addAttribute("postVo", postVo);
			
			return "/blog/blog-main";
		} else {
			System.out.println("[실패:존재하지 않는 블로그]");
			return "redirect:/";
		}

	}

	// 블로그 관리페이지-기본
	@RequestMapping("/{id}/admin/basic")
	public String adminBasic(@PathVariable("id") String id, Model model) {
		System.out.println("[BlogController.adminBasic]");

		BlogVo blogVo = blogService.getHeader(id);
		model.addAttribute("blogVo", blogVo);

		return "/blog/admin/blog-admin-basic";
	}

	// 블로그 수정
	@RequestMapping("/{id}/updateBlog")
	public String updateBlog(@PathVariable("id") String id, 
							@RequestParam("file") MultipartFile file,
							@ModelAttribute BlogVo blogVo) {
		System.out.println("[BlogController.updateBlog]");

		blogVo.setId(id);
		blogService.updateBlog(blogVo, file);

		return "redirect:/" + blogVo.getId() + "/admin/basic";
	}

	// 블로그 관리페이지-카테고리
	@RequestMapping("/{id}/admin/category")
	public String adminCate(@PathVariable("id") String id, 
							Model model) {
		System.out.println("[BlogController.adminCate]");

		BlogVo blogVo = blogService.getHeader(id);
		model.addAttribute("blogVo", blogVo);
		
		return "/blog/admin/blog-admin-cate";
	}

	// 카테고리 리스트 요청
	@ResponseBody
	@RequestMapping("/{id}/categoryList")
	public List<CategoryVo> categoryList(@PathVariable("id") String id) {
		System.out.println("[BlogController.categoryList]");

		List<CategoryVo> categoryList = blogService.getCategory(id);
		return categoryList;
	}
	
	// 카테고리 선택
	@RequestMapping("{id}/catePost")
	public String catePost(@RequestParam("cateNo") int cateNo, 
							@PathVariable("id") String id,
							Model model) {
		System.out.println("[BlogController.catePost]");
		System.out.println(cateNo);
		
		
		List<PostVo> postList = blogService.getPost(cateNo);
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setCateNo(cateNo);
		BlogVo blogVo = blogService.getBlogVo(id);
		List<CategoryVo> categoryList = blogService.getList(id);
		
		PostVo postVo =	blogService.getPostTop(cateNo);//최상단 포스트
		model.addAttribute("postList", postList);
		model.addAttribute("postVo", postVo);
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("categoryList", categoryList);
		
		return "/blog/blog-main";
	}

	// 카테고리 추가
	@ResponseBody
	@RequestMapping("/addCategory")
	public CategoryVo addCategory(@ModelAttribute CategoryVo categoryVo) {
		System.out.println("[BlogController.addCategory]");
		blogService.addCategory(categoryVo);

		return categoryVo;
	}

	// 카테고리 삭제
	@ResponseBody
	@RequestMapping("/delCategory")
	public int delCategory(@RequestParam("no") int cateNo) {
		System.out.println("[BlogController.delCategory]");
		int count = blogService.delCategory(cateNo);
		return count;
	}

	// 블로그 관리페이지-글쓰기
	@RequestMapping("/{id}/admin/writeForm")
	public String PostWriteForm(@PathVariable("id") String id, 
								Model model) {
		System.out.println("[BlogController.PostWriteForm]");

		List<CategoryVo> categoryList = blogService.getCategory(id);

		BlogVo blogVo = blogService.getHeader(id);

		model.addAttribute("blogVo", blogVo);
		model.addAttribute("categoryList", categoryList);

		return "/blog/admin/blog-admin-write";
	}

	// 글쓰기
	@RequestMapping("/post/write")
	public String write(@ModelAttribute PostVo postVo, 
						@RequestParam("id") String id) {
		System.out.println("[PostController.write]");
		blogService.setPost(postVo);
		String url = "redirect:/" + id + "/admin/writeForm";
		return url;
	}
}
