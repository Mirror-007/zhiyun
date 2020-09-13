package com.wyt.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wyt.util.GetRequestUtil;

import com.wyt.entity.Book;
import com.wyt.entity.Category;
import com.wyt.service.BookService;
import com.wyt.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/book")
public class BookController{

	@Autowired
	private BookService bookService;
	@Autowired
	private CategoryService categoryService;

	private static final  int pageNum = 3;


	/**
	 * @category 展示所有图书
	 * @return
	 */
	@RequestMapping("/showBooks")
	public String showBooks(){
		List<Book> books = bookService.showBooks();
		HttpServletRequest req = GetRequestUtil.getRequest();
		req.setAttribute("books", books);
		return "forward:/back/book/show.jsp";
	}

	/**
	 * @category 准备添加图书，回显所有二级类别
	 * @return
	 */
	@RequestMapping("/toAddBook")
	public String toAddBook(){
		List<Category> twos = categoryService.queryAllGradeTwo();
		GetRequestUtil.getRequest().setAttribute("twos", twos);
		return "forward:/back/book/add.jsp";
	}

	/**
	 * @category 添加图书信息
	 * @return
	 */
	@RequestMapping("/addBook")
	public String addBook(Book book,MultipartFile cover){
		String msg;
		HttpSession session = GetRequestUtil.getSession();
		System.out.println(book);
		Book bk = bookService.queryByBookName(book.getBookName());
		//添加书名没有重复，继续添加操作
		if(bk==null){
			//配置图片上传的路径
			ServletContext sc = GetRequestUtil.getSession().getServletContext();
			String realPath = sc.getRealPath("/back/img");
			File file = new File(realPath);
			if(!file.exists()){
				file.mkdir();
			}
			try {
				cover.transferTo(new File(realPath,cover.getOriginalFilename()));
//				FileUtils.copyFile(cover, new File(realPath+"/"+coverFileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
			//把需要添加的信息存到book对象中去
			Book bo = new Book();
			//利用UUID传id
			bo.setId(UUID.randomUUID().toString().replace("-", ""));
			bo.setBookName(book.getBookName());
			bo.setPrice(book.getPrice());
			bo.setDdPrice(book.getDdPrice());
			bo.setImage(cover.getOriginalFilename());
			bo.setPress(book.getPress());
			bo.setPublishDate(book.getPublishDate());
			bo.setParentCateName(book.getParentCateName());
			bo.setStorages(book.getStorages());
			bo.setWriter(book.getWriter());
			//把新建的对象作为参数添加图书信息
			bookService.addBook(bo);
			msg = "";
			session.setAttribute("msg", msg);
		}else{
			//添加图书重复，给出信息提示
			msg = "此书名已有，不能重复添加！";
			session.setAttribute("msg", msg);
			return "forward:/back/book/add.jsp";
		}
		return "redirect:/book/showBooks";
	}

	/**
	 * @category 准备修改信息，回显到页面
	 * @return
	 */
	@RequestMapping("/toUpdateBook")
	public String toUpdateBook(Book book){
		//遍历找出所有的二级类别
		List<Category> twos = categoryService.queryAllGradeTwo();
		HttpServletRequest req = GetRequestUtil.getRequest();
		HttpSession session = req.getSession();
		req.setAttribute("twos", twos);
		//通过传来的id查找到当前Book对象
		Book bo = bookService.queryById(book.getId());
		//格式化util.Date，使之可以在jsp页面回显
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sdf.format(bo.getPublishDate());
		req.setAttribute("publishDate", format);
		req.setAttribute("erjileibie", bo.getParentCateName());
		session.setAttribute("shu", bo);
		//把获取到的id传给updateBook，一遍传入修改信息进行修改
		session.setAttribute("idd", book.getId());
		return "forward:/back/book/update.jsp";
	}

	/**
	 * @category 修改图书信息
	 * @return
	 */
	@RequestMapping("/updateBook")
	public String updateBook(MultipartFile cover, Book book){
		System.out.println("11111111"+cover);
		HttpServletRequest req =GetRequestUtil.getRequest();
		HttpSession session = req.getSession();
		//配置图片上传的路径
		ServletContext sc = GetRequestUtil.getSession().getServletContext();
		String realPath = sc.getRealPath("/back/img");
		System.out.println(realPath);
		File file = new File(realPath);
		if(!file.exists()){
			file.mkdir();
		}
		if(!cover.isEmpty()) {
			try {
				//图片上传，没有修改则不上传
				cover.transferTo(new File(realPath, cover.getOriginalFilename()));
//				FileUtils.copyFile(cover, new File(realPath+"/"+coverFileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//取到当前图书的id
		String id = (String)session.getAttribute("idd");
		//取到当前图书对象
		Book b = (Book)session.getAttribute("shu");
		//把接收到的修改信息装进新的Book对象中进行修改
		Book bo = new Book();
		bo.setBookName(book.getBookName());
		bo.setPrice(book.getPrice());
		bo.setDdPrice(book.getDdPrice());

		//如果没有修改图片的时候，获取到该图书的文件名，重新添加进去
		String coverFileName = cover.getOriginalFilename();
		if(cover.isEmpty()){
			coverFileName = b.getImage();
		}
		bo.setImage(coverFileName);
		bo.setPress(book.getPress());
		bo.setPublishDate(book.getPublishDate());
		bo.setParentCateName(book.getParentCateName());
		bo.setStorages(book.getStorages());
		bo.setWriter(book.getWriter());

		System.out.println("book:"+bo);
		System.out.println("id"+id);
		bookService.update(bo,id);
		return "redirect:/book/showBooks";
	}

	/**
	 * @category 删除图书信息
	 * @return
	 */
	@RequestMapping("/deleteBook")
	public String deleteBook(@RequestParam("id") String id){
		bookService.deleteBook(id);
		return "redirect:/book/showBooks";
	}

	/**
	 * @category 根据接收到的条件进行模糊查询
	 * @return
	 */
	@RequestMapping("/likeQuery")
	public String likeQuery(String key,String content){
		List<Book> books = bookService.likeQueryByKey(key,content);
		HttpSession session = GetRequestUtil.getSession();
		session.setAttribute("books", books);
		return "forward:/back/book/show.jsp";
	}

	//前台主页面展示
	@RequestMapping("/bookList")
	public String bookList(Integer pageIndex,String grade1,String grade2){
		HttpServletRequest req = GetRequestUtil.getRequest();
		List<Category> ctg = null;
		List<Category> bos1 = null;
		List<Book> bos2 = null;
		String grade1Name = null;
		String grade = null;
		//第一次访问的时候，默认为第一页
		if(pageIndex == null){
			pageIndex = 1;
		}
		int endPageIndex = 0;
		System.out.println("2222222"+grade1);
		if(grade1!=null&&!"".equals(grade1)){
			ctg = categoryService.queryGradeTwoBelongToOne(grade1);
			//找到该一级类别下共有多少本书
			bos1 = categoryService.queryByGrade1(grade1,0,0);
			int size = 0;
			for (Category c : bos1.get(0).getCategories()) {
				size += c.getBooks().size();
			}

			//每页数据的起始下标
			int startIndex = (pageIndex-1)*pageNum+1;
			//不是最后一页的时候，每页的末尾数据下标
			int endIndex = startIndex+pageNum-1;

			//最后一页的当前页下标
			endPageIndex = size/pageNum;
			//不能整除
			if(size%pageNum != 0){
				//最后一页的下标
				endPageIndex = size/pageNum+1;
			}

			if(pageIndex == endPageIndex){
				//最后一页最后一个数据的下标
				endIndex = size;
			}

			//找出一级类别对应的所有书本并分页
			bos1 = categoryService.queryByGrade1(grade1,startIndex,endIndex);

			req.setAttribute("bos1", bos1);
			grade1Name = grade1;
			req.setAttribute("gN", grade1);
			if(endPageIndex == 0){
				pageIndex=0;
			}
		}
		if(grade2!=null&&!"".equals(grade2)){
			//通过接收二级类别名称，找到二级类别对象
			Category category2 = categoryService.queryBySecondName(grade2, 2);
			//找到二级类别对应的一级类别名称，当做参数，找出所有此一级类别下的二级类别
			ctg = categoryService.queryGradeTwoBelongToOne(category2.getParentCateName());
			//接收二级类别名称，找出对应所有的书本

			List<Book> bos3 = bookService.queryByGrade2(grade2,0,0);
			//最后一页的当前页下标
			int size = bos3.size();

			//每页数据的起始下标
			int startIndex = (pageIndex-1)*pageNum+1;
			//不是最后一页的时候，每页的末尾数据下标
			int endIndex = startIndex+pageNum-1;

			endPageIndex = size/pageNum;
			//不能整除
			if(size%pageNum != 0){
				//最后一页的下标
				endPageIndex = size/pageNum+1;
			}

			if(pageIndex == endPageIndex){
				//最后一页最后一个数据的下标
				endIndex = size;
			}

			bos2 = bookService.queryByGrade2(grade2,startIndex,endIndex);

			req.setAttribute("category2", category2);
			req.setAttribute("bos2", bos2);
			grade1Name = category2.getParentCateName();
			grade="1234";
			req.setAttribute("gr", grade2);
			if(endPageIndex == 0){
				pageIndex=0;
			}
		}
		req.setAttribute("grade", grade);
		req.setAttribute("pageIndex", pageIndex);
		req.setAttribute("endPageIndex", endPageIndex);
		req.setAttribute("grade1Name", grade1Name);
		req.setAttribute("ctg", ctg);
		return "forward:/front/main/book_list.jsp";
	}

	/**
	 * @category 图书详情
	 * @return
	 */
	@RequestMapping("/bookDetails")
	public String bookDetails(Book book){
		Book bookDetail = bookService.queryByBookName(book.getBookName());
		Category ca = categoryService.queryBySecondName(bookDetail.getParentCateName(), 2);
		String yiJi = ca.getParentCateName();
		HttpServletRequest req = GetRequestUtil.getRequest();
		req.setAttribute("bookDetail", bookDetail);
		req.setAttribute("yiJi", yiJi);
		return "forward:/front/main/book_detail.jsp";
	}









}
