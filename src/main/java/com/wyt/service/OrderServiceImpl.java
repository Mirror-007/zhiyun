package com.wyt.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wyt.dao.OrderDao;
import com.wyt.entity.Book;
import com.wyt.entity.CartItem;
import com.wyt.entity.Order;
import com.wyt.entity.OrderItem;
import com.wyt.util.GetRequestUtil;
import com.wyt.util.SnowFlakeUtil;
import com.wyt.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderDao orderDao;
	@Override
	public void updateBookStorages(String id, int storages) {
		orderDao.updateBook(id,storages,0);
	}

	@Override
	public void updateBookSaleCount(String id, int saleCount) {
		orderDao.updateBook(id, 0, saleCount);
	}

	//添加订单信息
	@Override
	public void addOrder() {

		HttpServletRequest req = GetRequestUtil.getRequest();
		HttpSession session = req.getSession();

		Map<String, CartItem> map = (Map<String,CartItem>)session.getAttribute("cart");
		Set<String> keys = map.keySet();
		String ss = null;
		//标记，检查是否每种图书都库存充足
		boolean flag = true;
		for (String id : keys) {
			BookService bs = new BookServiceImpl();
			CartItem cartItem = map.get(id);
			Book b = bs.queryById(cartItem.getBook().getId());
			//1.1判断在当前状态下是否每个图书都库存足够，足够才执行下面的操作
			flag = cartItem.getCount()<=b.getStorages();
			if(flag==false){
				flag=false;
				break;
			}
		}
		if(flag==true){
			for (String id : keys) {
				CartItem cartItem = map.get(id);
				//1.2修改库存
				updateBookStorages(cartItem.getBook().getId(),cartItem.getBook().getStorages()-cartItem.getCount());
				//2.修改图书销量
				updateBookSaleCount(cartItem.getBook().getId(),cartItem.getBook().getSaleCount()+cartItem.getCount());
			}

			//3.生成订单
			Order order = new Order();
			String uuid = UUID.randomUUID().toString().replace("-", "");
			order.setId(uuid);
			SnowFlakeUtil util = new SnowFlakeUtil(1, 1);
			long nextId = util.nextId();
			ss =""+nextId;
			order.setOrderNumber(ss);
			order.setOrderTime(new Date());

			Address addr = (Address)session.getAttribute("addressToOrder");
			order.setAddress(addr.getAddress());
			order.setAddressee(addr.getAddressee());
			order.setAddressId(addr.getId());
			order.setStatus("未支付");
			order.setUserId(addr.getUserId());
			double total = (double)session.getAttribute("total");
			order.setTotal(total);

			//4.生成订单项
			for (String id : keys) {
				CartItem cartItem = map.get(id);
				List<OrderItem> list = new ArrayList<OrderItem>();
				if(cartItem.isStatus()){
					OrderItem orderItem = new OrderItem();
					orderItem.setOrderId(uuid);
					orderItem.setBookId(cartItem.getBook().getId());
					orderItem.setCount(cartItem.getCount());
					orderItem.setId(UUID.randomUUID().toString().replace("-", ""));
					orderItem.setMiniPrice(cartItem.getMiniPrice());
					orderItem.setPrice(cartItem.getBook().getDdPrice());
					list.add(orderItem);
					//添加数据到订单项表
					addOrderItem(orderItem);
				}
				//添加所有的订单项
				order.setOrderItems(list);
			}
			//添加到订单表
			addBookOrder(order);
		}
		//5.清除购物车
		session.removeAttribute("cart");
		//6.把订单信息存储到作用域中，以便订单展示
		req.setAttribute("danHao", ss);


	}

	@Override
	public void addBookOrder(Order order) {
		orderDao.addBookOrderOrOrderItem(order,null);
	}

	@Override
	public void addOrderItem(OrderItem orderItem) {
		orderDao.addBookOrderOrOrderItem(null,orderItem);

	}





}
