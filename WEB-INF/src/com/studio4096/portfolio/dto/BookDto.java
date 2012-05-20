package com.studio4096.portfolio.dto;

import java.io.Serializable;
import java.util.Map;

import com.studio4096.portfolio.entity.Book;

public class BookDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4262557622356655964L;
	public Book book;
	public Book getBook() { return book; }
	public Map<Long,PageDto> pageMap;
	public Map<Long, PageDto> getPageMap() { return pageMap; }
	
}
