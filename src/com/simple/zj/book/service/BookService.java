package com.simple.zj.book.service;

import java.util.List;
import java.util.Map;

import com.simple.bsp.common.util.DataGridModel;
import com.simple.zj.book.form.SonBook;
import com.simple.zj.book.po.Book;

public interface BookService {
	
	public Map<String,Object> getBookPage(DataGridModel dgm, SonBook form);
	
	public int saveBook(Book book);
	
	public int updateBook(Book book);
	
	public int[] delBook(List<String> idList);

}
