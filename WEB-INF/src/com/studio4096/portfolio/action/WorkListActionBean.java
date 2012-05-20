package com.studio4096.portfolio.action;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.util.UrlBuilder;

import com.studio4096.portfolio.dto.WorkDto;
import com.studio4096.portfolio.entity.Book;
import com.studio4096.portfolio.entity.ImageData;
import com.studio4096.portfolio.entity.Page;
import com.studio4096.portfolio.exception.ServiceException;
import com.studio4096.portfolio.service.BookService;
import com.studio4096.portfolio.service.ImageService;
import com.studio4096.portfolio.service.PageService;

@UrlBinding("/works.html")
public class WorkListActionBean extends BaseActionBean {
	Log log = new Log4JLogger(this.getClass().getName());

	private List<WorkDto> works;
	public List<WorkDto> getWorks() { return works; }
	
	@DefaultHandler
	public Resolution display() {
		List<Book> books = null;
		Map<Long,ImageData> imageDataMap;
		try {
			books = BookService.findBooks(getContext());
			imageDataMap =ImageService.findImageDataMap();
		} catch (ServiceException e) {
			if(log.isErrorEnabled()){ log.error("",e); }
			return null;
		}
		
		works =new LinkedList<WorkDto>();
		if(books == null || books.size() < getSetting().getMinBookCounts()){
			List<Page> pages;
			try {
				pages = PageService.findAllPages(getContext());
			} catch (ServiceException e) {
				if(log.isErrorEnabled()){ log.error("",e); }
				return null;
			}
			for(Page page : pages){
				WorkDto work =new WorkDto();
				works.add(work);
				UrlBuilder urlBuilder;
				urlBuilder = new UrlBuilder(Locale.getDefault(), SlideshowActionBean.class, true);
				urlBuilder.addParameter("bookId", page.getBookId());
				urlBuilder.addParameter("pageId", page.getPageId());
				work.linkUrl = urlBuilder.toString();
				work.imageData =imageDataMap.get(page.getImageId());
			}
		}else{
			for(Book book : books){
				WorkDto work =new WorkDto();
				works.add(work);
				UrlBuilder urlBuilder;
				urlBuilder = new UrlBuilder(Locale.getDefault(), SlideshowActionBean.class, true);
				urlBuilder.addParameter("bookId", book.getBookId());
				work.linkUrl = urlBuilder.toString();
				work.imageData =imageDataMap.get(book.getCoverImageId());
			}
		}
		return new ForwardResolution("/WEB-INF/view/works.jsp");
	}

}
