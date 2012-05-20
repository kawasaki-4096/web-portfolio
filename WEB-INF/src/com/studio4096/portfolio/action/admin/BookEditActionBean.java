package com.studio4096.portfolio.action.admin;

import static com.studio4096.portfolio.cnstants.Enabled.TRUE;

import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;

import com.studio4096.portfolio.cnstants.Enabled;
import com.studio4096.portfolio.entity.Book;
import com.studio4096.portfolio.entity.Page;
import com.studio4096.portfolio.exception.ServiceException;
import com.studio4096.portfolio.service.BookService;
import com.studio4096.portfolio.service.PageService;

public class BookEditActionBean extends BaseAdminActionBean{
	
	Log log = new Log4JLogger(this.getClass().getName());
    private  Book book;
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
    
	private Long bookId;
	public Long getBookId() { return bookId; }
	public void setBookId(Long bookId) { this.bookId = bookId; }
	
	private List<Page> pageList;
	public List<Page> getPageList() { return pageList; }
	
	@DefaultHandler
    public Resolution display() {
		try {
			if(bookId == null){
				book = new Book();
				book.setCoverImageId(0L);
				book.setFirstPageId(0L);
				book.setPublicFlag(Enabled.TRUE.VAL);
				book.setEnabled(Enabled.TRUE.VAL);
				BookService.saveBook(book);
				bookId =book.getBookId();
			}else {
				book=BookService.getBook(bookId);
			}
			
			pageList=PageService.findPages(bookId);
		} catch (ServiceException e) {
			if (log.isErrorEnabled()) {
				log.error("", e);
			}
			return null;
		}
		return new ForwardResolution("/WEB-INF/view/admin/bookEdit.jsp");
	}
	
    public Resolution edit() {
    	
		book.setPublicFlag(TRUE.VAL);
		
		try {
			BookService.saveBook(book);
		} catch (ServiceException e) {
			if (log.isErrorEnabled()) {
				log.error("", e);
			}
		}
		return new ForwardResolution("/WEB-INF/view/admin/bookEdit.jsp");
    }

}
