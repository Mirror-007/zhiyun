package com.wyt.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wyt.entity.Book;
import com.wyt.entity.CartItem;
import com.wyt.util.GetRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CartItemServiceImpl implements CartItemService{

	@Autowired
	private BookService bookService;

	/**
	 * @category 计算总共节省的钱和总共需要花的钱
	 * @param map
	 */
	public void calculateSaveAndTotal(Map<String, CartItem> map){
		//总共节省的钱
		double save = 0;
		//总计多少钱
		double total = 0;
		Set<String> keys = map.keySet();
		for (String id : keys) {
			CartItem item = map.get(id);
			if(item.isStatus()){
				save +=(item.getBook().getPrice()-item.getBook().getDdPrice())*item.getCount();
				total +=item.getMiniPrice();
			}
		}

		HttpSession session = GetRequestUtil.getSession();
		//将得到的结果存到session作用域中
		session.setAttribute("save", save);
		session.setAttribute("total", total);

	}

	/**
	 * @category 添加购物车数据
	 */
	@Override
	public void add(String id) {
		Book book = bookService.queryById(id);
		HttpSession session = GetRequestUtil.getSession();
		Map<String,CartItem> map = (Map<String,CartItem>)session.getAttribute("cart");
		if(map==null){
			//添加一条数据到购物车
			map = new HashMap<>();
			CartItem cartItem = new CartItem();
			cartItem.setBook(book);
			cartItem.setCount(1);
			cartItem.setMiniPrice(book.getDdPrice()*cartItem.getCount());
			cartItem.setStatus(true);
			map.put(id, cartItem);
			session.setAttribute("cart", map);
			//计算节省多少钱和总价
			calculateSaveAndTotal(map);
		}else{
			if(!map.containsKey(id)){
				CartItem cartItem = new CartItem();
				cartItem.setBook(book);
				cartItem.setCount(1);
				cartItem.setMiniPrice(book.getDdPrice()*cartItem.getCount());
				cartItem.setStatus(true);
				map.put(id, cartItem);
				session.setAttribute("cart", map);
				calculateSaveAndTotal(map);
			}else{
				CartItem item = map.get(id);
				if(item.isStatus()==true){
					item.setBook(book);
					item.setCount(item.getCount()+1);
					item.setMiniPrice(item.getBook().getDdPrice()*item.getCount());
					map.put(id, item);
					session.setAttribute("cart", map);
					calculateSaveAndTotal(map);
				}else{
					item.setStatus(true);
					item.setCount(1);
					item.setMiniPrice(item.getBook().getDdPrice()*item.getCount());
					item.setBook(book);
					map.put(id, item);
					session.setAttribute("cart", map);
					calculateSaveAndTotal(map);
				}
			}
		}
	}

	/**
	 * @category 删除一条购物数据到恢复区
	 */
	@Override
	public void delete(String id) {
		HttpSession session = GetRequestUtil.getSession();
		Map<String,CartItem> map = (Map<String,CartItem>)session.getAttribute("cart");
		CartItem item = map.get(id);
		item.setStatus(false);
		item.setMiniPrice(item.getBook().getDdPrice()*item.getCount());
		item.setBook(bookService.queryById(id));
		map.put(id, item);
		session.setAttribute("cart", map);
		calculateSaveAndTotal(map);
	}

	/**
	 * @category 恢复删除过的购物车数据
	 */
	@Override
	public void recover(String id) {
		HttpSession session = GetRequestUtil.getSession();
		Map<String,CartItem> map = (Map<String,CartItem>)session.getAttribute("cart");
		CartItem item = map.get(id);
		item.setStatus(true);
		item.setBook(bookService.queryById(id));
		item.setMiniPrice(item.getBook().getDdPrice()*item.getCount());
		item.setCount(1);
		map.put(id, item);
		session.setAttribute("cart", map);
		calculateSaveAndTotal(map);
	}

	@Override
	public void update(String id,int count) {
		String msgg=null;
		HttpServletRequest request = GetRequestUtil.getRequest();
		HttpSession session = request.getSession();
		Map<String,CartItem> map = (Map<String,CartItem>)session.getAttribute("cart");
		CartItem item = map.get(id);
		Book book = bookService.queryById(id);
		if(count>book.getStorages()){
			msgg = "库存余量不足";
		}
		if(count>0&&count<=book.getStorages()){
			msgg = null;
			item.setCount(count);
			item.setBook(book);
			item.setMiniPrice(book.getDdPrice()*item.getCount());
			map.put(id, item);
			session.setAttribute("cart", map);
			calculateSaveAndTotal(map);
		}
		request.setAttribute("msgg", msgg);
	}













}
