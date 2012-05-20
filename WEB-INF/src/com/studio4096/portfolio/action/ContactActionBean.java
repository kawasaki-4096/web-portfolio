package com.studio4096.portfolio.action;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;

import com.studio4096.portfolio.cnstants.ApplicationSetting;
@UrlBinding("/contact.html")
public class ContactActionBean extends BaseActionBean{
	Log log = new Log4JLogger(this.getClass().getName());

	@DefaultHandler
	public Resolution display(){
		String email=ApplicationSetting.getInstance().getContactEMailAddress();
		char[] parts = email.toCharArray();
		int max =parts.length;
		StringBuilder sb =new StringBuilder();
		sb.append('\'');
		sb.append(parts[0]);
		sb.append('\'');
		for(int i =1;i < max;i++){
			sb.append("+'");
			sb.append(parts[i]);
			sb.append('\'');
		}
		getContext().getRequest().setAttribute("mailAddress", sb.toString());
		
		return new ForwardResolution("/WEB-INF/view/contact.jsp");
	}
}
