package com.wyt.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import com.wyt.entity.Book;
import com.wyt.entity.Category;
import com.wyt.service.BookService;
import com.wyt.service.BookServiceImpl;
import com.wyt.service.CategoryService;
import com.wyt.service.CategoryServiceImpl;
import com.wyt.util.GetRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cate")
public class CategoryController{

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private BookService bookService;

	/**
	 * @category 展示所有类别信息
	 * @return
	 */
	@RequestMapping("/showCategories")
	public String showCategories(){
		List<Category> list = categoryService.queryAll();
		HttpServletRequest req = GetRequestUtil.getRequest();
		req.setAttribute("LIST", list);
		return "forward:/back/category/show.jsp";
	}

	/**
	 * @category 添加一级类别
	 * @return
	 */
	@RequestMapping("/addFirstGrade")
	public String addFirstGrade(String message,String gradeName){
		Category cate = categoryService.queryByName(gradeName);
		HttpServletRequest req = GetRequestUtil.getRequest();
		//判断填入的一级类别是否已经存在
		if(cate!=null){
			//一级类别已经存在，给出提示信息
			message = "已有的一级类别";
			req.setAttribute("message", message);
			return "forward:/back/category/add-first.jsp";
		}else{
			String str = UUID.randomUUID().toString().replace("-", "");
			Category ca = new Category();
			ca.setId(str);
			ca.setCateName(gradeName);
			ca.setParentCateName("");
			ca.setGrade(1);
			categoryService.addFirstGrade(ca);
			return "redirect:/cate/showCategories";
		}
	}

	/**
	 * @category 查询所有一级类别，准备添加二级类别
	 * @return
	 */
	@RequestMapping("/toAddSecondGrade")
	public String toAddSecondGrade(){
		List<Category> li = categoryService.queryAllGradeOne(1);
		HttpServletRequest req = GetRequestUtil.getRequest();
		req.setAttribute("li", li);
		return "forward:/back/category/add-second.jsp";
	}

	/**
	 * @category 添加二级类别
	 * @return
	 */
	@RequestMapping("/addSecondGrade")
	public String addSecondGrade(String mm,String secName,String firName){
		Category cate = categoryService.queryBySecondName(secName,2);
		HttpServletRequest req = GetRequestUtil.getRequest();
		//判断填入内容是否已经存在
		if(cate != null){
			//二级类别已经存在，给出提示信息
			mm = "这个名字已经有了，换一个吧！";
			req.setAttribute("mm", mm);
			return "forward:/back/category/add-second.jsp";
		}else{
			String str = UUID.randomUUID().toString().replace("-", "");
			Category ca = new Category();
			ca.setId(str);
			ca.setCateName(secName);
			ca.setParentCateName(firName);
			ca.setGrade(2);
			categoryService.addSecondGrade(ca);
			return "redirect:/cate/showCategories";
		}

	}

	/**
	 * @category 删除一个类别
	 * @return
	 */
	@RequestMapping("/deleteOne")
	public String deleteOne(String aid){
		categoryService.deleteOne(aid);
		return "redirect:/cate/showCategories";
	}

	/**
	 * @category 展示前台数据
	 * @return
	 */
	@RequestMapping("/showFrontCate")
	public String showFrontCate(){
		//分类展示数据
		List<Category> list = categoryService.queryAll();
		HttpServletRequest req = GetRequestUtil.getRequest();
		HttpSession session = req.getSession();
		session.setAttribute("LIST", list);
		//编辑推荐数据展示
		List<Book> recommends = bookService.queryFrontCondition(2,0,0);
		req.setAttribute("recommends", recommends);
		//热销图书
		List<Book> hots = bookService.queryFrontCondition(0,4,0);
		req.setAttribute("hots", hots);
		//最新上架图书
		List<Book> news = bookService.queryFrontCondition(0,0,4);
		req.setAttribute("news", news);
		return "forward:/front/main/main.jsp";
	}



}