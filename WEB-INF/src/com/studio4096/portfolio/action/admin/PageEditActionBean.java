package com.studio4096.portfolio.action.admin;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SimpleMessage;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.validation.Validate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;

import com.studio4096.portfolio.cnstants.Enabled;
import com.studio4096.portfolio.entity.ImageData;
import com.studio4096.portfolio.entity.Page;
import com.studio4096.portfolio.exception.ServiceException;
import com.studio4096.portfolio.service.ImageService;
import com.studio4096.portfolio.service.PageService;

public class PageEditActionBean extends BaseAdminActionBean {
	
	Log log = new Log4JLogger(this.getClass().getName());

	private Page page;

	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	private Long pageId;
	public Long getPageId() {
		return pageId;
	}
	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}

	@DefaultHandler
	public Resolution display() {
		if(pageId!=null){
		
			try {
				page = PageService.getPage(pageId);
				if (page != null && page.imageId > 0L) {
					
					ImageData imageData = ImageService.getImageData(
							page.getImageId(), getContext());
					getContext().getRequest().setAttribute("imageData",
							imageData);
				}
			} catch (ServiceException e) {
				getContext().getMessages().add(new SimpleMessage("DB error."));
				if(log.isErrorEnabled()){ log.error("", e); }
			}
		}
		
		return new ForwardResolution("/WEB-INF/view/admin/pageEdit.jsp");
	}
	
	public Resolution newPage(){
		
		try {
			page.setPublicFlag(Enabled.FALSE.VAL);
			page.setSortOrder(1);
			page.setImageId(0L);
			PageService.savePage(page);
			System.out.println(page.getPageId());
		} catch (ServiceException e) {
			getContext().getMessages().add(new SimpleMessage("DB error."));
			if(log.isErrorEnabled()){ log.error("", e); }
			return null;
		}
		long pageId =page.getPageId();
		StringBuilder html =new StringBuilder();
        html.append("<li class=\"sys-each-page\" id=\"sys-page-"+pageId+"\">pageId:"+pageId+"(x)");
        html.append("  <input name=\"pageId\" type=\"hidden\" value=\""+pageId+"\"/>");
        html.append("</li>");
		return new StreamingResolution("text/html", html.toString());
	}
	
	@Validate(on={"sort"},required=true)
	private Long bookId;
	public void setBookId(Long bookId) { this.bookId = bookId; }
	public Long getBookId() { return bookId; }
	
	@Validate(on={"sort"},required=true)
	private Long[] pageIds;
	public void setPageIds(Long[] pageIds) { this.pageIds = pageIds; }
	public Long[] getPageIds() { return pageIds; }
	public Resolution sort(){
		try {
			PageService.sortPages(bookId, pageIds);
		} catch (ServiceException e) {
			getContext().getMessages().add(new SimpleMessage("DB error."));
			if(log.isErrorEnabled()){ log.error("", e); }
			return null;
		}
		// TODO kawasaki send error.
		return null;
	}

	public Resolution edit() {
		page.setPublicFlag(Enabled.FALSE.VAL);
		
		try {
			PageService.savePage(page);
		} catch (ServiceException e) {
			if(log.isErrorEnabled()){
				log.error("", e);
			}
			return null;
		}
		
		
		return new ForwardResolution("/WEB-INF/view/admin/pageEdit.jsp");
	}
	
	public Resolution setImage(){
		try {
			PageService.setPageImage(page.pageId, page.imageId);
		} catch (ServiceException e) {
			getContext().getMessages().add(new SimpleMessage("DB error."));
			if(log.isErrorEnabled()){ log.error("", e); }
			return null;
		}
		return null;
	}
	public Resolution deletePage(){
		try {
			PageService.deletePage(page.getPageId());
		} catch (ServiceException e) {
			getContext().getMessages().add(new SimpleMessage("DB error."));
			if(log.isErrorEnabled()){ log.error("", e); }
			return null;
		}
		StringBuilder html =new StringBuilder();
		return new StreamingResolution("text/html", html.toString());
	}

}
