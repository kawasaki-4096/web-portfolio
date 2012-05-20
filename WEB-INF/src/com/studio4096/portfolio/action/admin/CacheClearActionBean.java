package com.studio4096.portfolio.action.admin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;

import com.studio4096.portfolio.cnstants.Globals;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;

public class CacheClearActionBean extends BaseAdminActionBean{
	Log log = new Log4JLogger(this.getClass().getName());

	@DefaultHandler
	public Resolution clearCache(){
		getContext().getBookCache().clear();
		getContext().getImageDataCache().clear();
		getContext().getServletContext().removeAttribute(Globals.SITE_CACHE);
		return null;
	}

}
