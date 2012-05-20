package com.studio4096.portfolio.action.admin;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.util.UrlBuilder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;

import com.studio4096.portfolio.dto.WorkDto;
import com.studio4096.portfolio.entity.Book;
import com.studio4096.portfolio.entity.ImageData;
import com.studio4096.portfolio.exception.ServiceException;
import com.studio4096.portfolio.service.BookService;
import com.studio4096.portfolio.service.ImageService;

public class BookListActionBean extends BaseAdminActionBean{
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
			if (log.isErrorEnabled()) {
				log.error("", e);
			}
			return new ErrorResolution(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		works =new LinkedList<WorkDto>();
		for (Book book : books) {
			WorkDto work = new WorkDto();
			works.add(work);
			UrlBuilder urlBuilder;
			urlBuilder = new UrlBuilder(Locale.getDefault(),
					BookEditActionBean.class, true);
			urlBuilder.addParameter("bookId", book.getBookId());
			work.linkUrl = urlBuilder.toString();
			work.imageData = imageDataMap.get(book.getCoverImageId());
		}
		return new ForwardResolution("/WEB-INF/view/admin/bookList.jsp");
	}


}
