package com.studio4096.portfolio.action.admin;

import javax.servlet.http.HttpServletResponse;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.validation.Validate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;

import com.studio4096.portfolio.action.BaseActionBean;
import com.studio4096.portfolio.cnstants.Globals;
import com.studio4096.portfolio.entity.AppUser;
import com.studio4096.portfolio.exception.ServiceException;
import com.studio4096.portfolio.service.TempGlobalService;

public class AdminLoginActionBean extends BaseActionBean{
	Log log = new Log4JLogger(this.getClass().getName());
	
	@Validate(required=true,trim=true,on={"login"},maxlength=256)
	private String userId;
	@Validate(required=true,trim=true,on={"login"},maxlength=256)
	private String password;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@DefaultHandler
    public Resolution showForm() {
		return new ForwardResolution("/WEB-INF/view/admin/login.jsp");
	}
	
	public Resolution login() {
    	AppUser user =null;
		try {
			user = TempGlobalService.getUser(userId,password);
			getContext().getSession().setAttribute(Globals.ADMIN_LOGIN_INFO, user);
		} catch (ServiceException e) {
			return new ErrorResolution(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
    	if(user == null){
    		return new ForwardResolution("/WEB-INF/view/admin/login.jsp");
    	}
    	
		return new ForwardResolution(BookListActionBean.class);
    	
    }
    public Resolution logout() {
    	getContext().getSession().invalidate();
		return new ForwardResolution("/WEB-INF/view/admin/login.jsp");
    }

}
