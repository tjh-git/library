package com.simple.zj.book.cantroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.common.util.Dictionary;
import com.simple.bsp.common.util.NextID;
import com.simple.bsp.security.po.PubUsers;
import com.simple.zj.book.form.SonBook;
import com.simple.zj.book.po.Book;
import com.simple.zj.book.service.BookService;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	
	@RequestMapping(value = "/book/book", method = RequestMethod.GET)
	public String skip(){
		
		return "zj/book/book";
		
	}
	
	@RequestMapping(value = "/book/bookList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> bookList(DataGridModel dgm, SonBook form){
		
		Map<String, String> tsyyMap = Dictionary.getDictionary("TSYY");
		Map<String, String> tslxMap = Dictionary.getDictionary("TSLX");
		
		Map<String, Object> map = bookService.getBookPage(dgm, form);
		
		if(map.get("rows")!=null){
			List<SonBook> list = (List) map.get("rows");
			
			for(SonBook sonBook : list){
				sonBook.setTsyyName((String)tsyyMap.get(sonBook.getTsyy()));
				sonBook.setTslxName((String)tslxMap.get(sonBook.getYlsx()));
			}
		}
		return map;
		
	}
	
	@RequestMapping(value = "/book/addBook", method = RequestMethod.GET)
	public String addBook(Model model, HttpServletRequest req){
		
		List<Map<String,String>> tsyylist = Dictionary.getListDictionary("TSYY");
		List<Map<String,String>> tslxlist = Dictionary.getListDictionary("TSLX");
		req.setAttribute("tsyylist", tsyylist);
		req.setAttribute("tslxlist", tslxlist);
		return "zj/book/addBook";
		
	}
	
	@RequestMapping(value = "/book/addBookTwo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> addBookTwo(Book book, HttpServletRequest req){
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String orgId = "";
		String userId = "";
		if (principal instanceof PubUsers) {
            PubUsers user = (PubUsers) principal;
			orgId = user.getUserOrg();
			userId = user.getUserId();
		}
		book.setId(NextID.getUUID());// 主键
		book.setOrgId(orgId);
		book.setUserId(userId);
		
		Map<String, String> map = new HashMap<String,String>();
		
		try {
			if(bookService.saveBook(book)>0){
				map.put("mes", "添加成功");
			} else {
				map.put("mes", "添加失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "添加失败");
		}
		return map;
		
	}
	
	@RequestMapping(value = "/book/updateBookList", method = RequestMethod.GET)
	public String updateBookList(Model model, HttpServletRequest req){
		
		List<Map<String,String>> tsyylist = Dictionary.getListDictionary("TSYY");
		List<Map<String,String>> tslxlist = Dictionary.getListDictionary("TSLX");
		req.setAttribute("tsyylist", tsyylist);
		req.setAttribute("tslxlist", tslxlist);
		return "zj/book/updateBook";
     }
	
	@RequestMapping(value = "/book/updateBookTwo", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updateBookTwo(Book book,HttpServletRequest req){
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String orgId = "";
		if (principal instanceof PubUsers) {
			PubUsers user = (PubUsers) principal;
			orgId = user.getUserOrg();
		}
		
		book.setOrgId(orgId);
		
		Map<String, String> map = new HashMap<String,String>();
		try {
			if(bookService.updateBook(book)>0){
				map.put("mes", "修改成功");
			} else {
				map.put("mes", "修改失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "修改失败");
		}
		return map;
		
	}
	
	@RequestMapping(value = "/book/delBook", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> delBook(@RequestParam("id") List<String> idList){
		
		Map<String, String> map = new HashMap<String, String>(); 
		try {
			int[] result = bookService.delBook(idList);
			
			int sum = 0;
			
			for(int j = 0; j < result.length; j ++){
				sum += result[j];
			}
			
			map.put("mes", "删除成功["+sum+"]条记录");
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("mes", "删除失败");
		}
		return map;
		
	}
	
}
