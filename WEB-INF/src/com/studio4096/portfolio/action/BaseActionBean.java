package com.studio4096.portfolio.action;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Before;

import com.studio4096.portfolio.cnstants.ApplicationSetting;
import com.studio4096.portfolio.dto.ClientInfoDto;

public abstract class BaseActionBean implements ActionBean{
	protected ClientInfoDto clientInfo;
	public ClientInfoDto getClientInfo() { return clientInfo; }
	
	protected String pageTitle = ApplicationSetting.getInstance().getSiteName();

    public String getPageTitle() { return pageTitle; }
	public void setPageTitle(String pageTitle) { this.pageTitle = pageTitle; }

	private PortfolioActionBeanContext context;

    public PortfolioActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = (PortfolioActionBeanContext)context; }
    
    protected void addPageTitle(String pageTitle){
    	this.pageTitle = this.pageTitle + pageTitle;
    }
    
    protected ApplicationSetting getSetting(){
    	return ApplicationSetting.getInstance();
    }
    @Before
    protected void prepare(){
    	clientInfo =getContext().getClientInfo();
    }
    enum Scope{APPLICATION,SESSION,REQUEST}
}
