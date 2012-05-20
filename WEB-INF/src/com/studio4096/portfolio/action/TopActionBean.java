package com.studio4096.portfolio.action;

import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.studio4096.portfolio.entity.Site;
import com.studio4096.portfolio.exception.ServiceException;
import com.studio4096.portfolio.service.TempGlobalService;
@UrlBinding("/index.html")
public class TopActionBean extends BaseActionBean {
	Log log = new Log4JLogger(this.getClass().getName());

	@DefaultHandler
	public Resolution display() {
		
		long topBookId;
		try {
			Site site =getContext().getSiteCache();
			if(site == null){
				site=TempGlobalService.getSite();
				getContext().setSiteCache(site);
			}
			if(site==null){
				topBookId =1L;
			}else{
				topBookId=site.getTopBookId();
			}
		} catch (ServiceException e) {
			if(log.isErrorEnabled()){ log.error("",e); }
			topBookId =1L;
		}
		getContext().getImageDataCache();
		
		ForwardResolution forword;
		forword = new ForwardResolution(SlideshowActionBean.class);
		forword.addParameter("bookId", topBookId);
		System.out.println(forword.getUrl(Locale.getDefault()));
		return forword;
	}

}
