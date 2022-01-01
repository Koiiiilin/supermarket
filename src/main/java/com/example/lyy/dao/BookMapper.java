package com.example.lyy.dao;

import com.example.lyy.pojo.Book;
import com.example.lyy.pojo.Order;
import com.example.lyy.pojo.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {
	public List<Book> findAllProduct();
	public int addOrder(Order order);
	public Book findBookByid(int id);
	int addTocart(OrderItem orderItem);
	List<Order> findOrderbyUid(int id);
	List<OrderItem> findOrderItembyid(String id);
}
