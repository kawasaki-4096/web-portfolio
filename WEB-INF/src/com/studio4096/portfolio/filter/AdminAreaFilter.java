package com.studio4096.portfolio.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.util.StringUtil;
import net.sourceforge.stripes.util.UrlBuilder;

import com.studio4096.portfolio.action.admin.AdminLoginActionBean;
import com.studio4096.portfolio.cnstants.Globals;

public class AdminAreaFilter implements Filter{

	public void destroy() {
	}
    private static Set<String> publicUrls = new HashSet<String>();

    static {
    	@SuppressWarnings("unchecked")
		Class<? extends ActionBean>[] actions =new Class[]{
    		AdminLoginActionBean.class
    	};
    	for(Class<? extends ActionBean> action: actions){
			UrlBuilder urlBuilder = new UrlBuilder(Locale.getDefault(), action,true);
			publicUrls.add(urlBuilder.toString());
    	}
    }

    /** Does nothing. */
    public void init(FilterConfig filterConfig) throws ServletException { }

    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getSession().getAttribute(Globals.ADMIN_LOGIN_INFO) != null) {
            filterChain.doFilter(request, response);
        }
        else if ( isPublicResource(request) ) {
            filterChain.doFilter(request, response);
		} else {
            // Redirect the user to the login page, noting where they were coming from
            @SuppressWarnings("unused")
			String targetUrl = StringUtil.urlEncode(request.getServletPath());
            
			UrlBuilder urlBuilder = new UrlBuilder(Locale.getDefault(),
					AdminLoginActionBean.class, true);
			response.sendRedirect(request.getContextPath()
					+ urlBuilder.toString());
        }
    }

    /**
     * Method that checks the request to see if it is for a publicly accessible resource
     */
    protected boolean isPublicResource(HttpServletRequest request) {
        String resource = request.getServletPath();

        return publicUrls.contains(request.getServletPath())
                || (!resource.endsWith(".jsp") && !resource.endsWith(".action"));
    }
}
