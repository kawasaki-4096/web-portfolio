package com.studio4096.portfolio.action;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.Validate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;

import com.studio4096.portfolio.dto.BookDto;
import com.studio4096.portfolio.dto.PageDto;
import com.studio4096.portfolio.entity.ImageData;
import com.studio4096.portfolio.entity.Page;
import com.studio4096.portfolio.exception.ServiceException;
import com.studio4096.portfolio.service.BookService;
import com.studio4096.portfolio.service.ImageService;

@UrlBinding("/show/{bookId}/{pageId}.html")
public class SlideshowActionBean extends BaseActionBean {
	Log log = new Log4JLogger(this.getClass().getName());

	@Validate(required = false)
	private Long bookId;
	public Long getBookId() { return bookId; }
	public void setBookId(Long bookId) { this.bookId = bookId; }

	@Validate(required = false)
	private Long pageId;
	public Long getPageId() { return pageId; }
	public void setPageId(Long pageId) { this.pageId = pageId; }

	private ImageData imageData;
	public ImageData getImageData() { return imageData; }
	
	private Page prevPage;
	public Page getPrevPage() {return prevPage; }

	private Page nextPage;
	public Page getNextPage() { return nextPage; }

	@DefaultHandler
	public Resolution display() {
		if(bookId ==null){
			bookId = 1L;// FIXME kawasaki 
		}
		
		
		BookDto bookDto;
		Map<Long, PageDto> pageMap;
		try {
			bookDto = BookService.getBookDto(bookId, getContext());
			if(bookDto == null){
				return new ErrorResolution(HttpServletResponse.SC_NOT_FOUND);
			}
			pageMap =bookDto.pageMap;
		} catch (ServiceException e) {
			if(log.isErrorEnabled()){
				log.error("",e);
			}
			return null;
		}
		if(pageId == null){
			pageId = bookDto.book.getFirstPageId();
		}
		PageDto portfolioPage = pageMap.get(pageId);
		if (portfolioPage == null ) {
			return new ErrorResolution(HttpServletResponse.SC_NOT_FOUND);
		}
		PageDto prev, next;
		prev = portfolioPage.preview;
		if (prev != null) {
			prevPage = prev.current;
		}
		next = portfolioPage.next;
		if (next != null) {
			nextPage = next.current;
		}

		Page page = portfolioPage.current;
		if (page == null) {
			return new ErrorResolution(HttpServletResponse.SC_NOT_FOUND);
		}
		try {
			this.imageData = ImageService.getImageData(page.getImageId(),getContext());
		} catch (ServiceException e) {
			if(log.isErrorEnabled()){ log.error("",e); }
			return null;
		}
		if (imageData != null) {
			addPageTitle(imageData.getTitle());
		}
		return new ForwardResolution("/WEB-INF/view/slideshow.jsp");
	}


}
