package com.simple.zj.book.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.bsp.common.util.DataGridModel;
import com.simple.zj.book.dao.BookDao;
import com.simple.zj.book.form.SonBook;
import com.simple.zj.book.po.Book;
import com.simple.zj.book.service.BookService;

@Service("bookService")
public class BookImplService implements BookService {

	@Autowired
	private BookDao bookDao;
	
	@Override
	public Map<String, Object> getBookPage(DataGridModel dgm, SonBook form) {
		
		return bookDao.getBookPage(dgm, form);
	}

	@Override
	public int saveBook(Book book) {
		
		return bookDao.saveBook(book);
	}

	@Override
	public int updateBook(Book book) {
		// TODO Auto-generated method stub
		return bookDao.updateBook(book);
	}

	@Override
	public int[] delBook(List<String> idList) {
		// TODO Auto-generated method stub
		return bookDao.delBook(idList);
	}

}
