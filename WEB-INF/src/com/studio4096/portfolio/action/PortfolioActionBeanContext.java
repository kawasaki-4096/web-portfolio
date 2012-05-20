package com.studio4096.portfolio.action;

import static com.studio4096.portfolio.cnstants.DefaultScreenSize.W1024H768;
import static com.studio4096.portfolio.cnstants.DefaultScreenSize.W480H320;
import static nl.bitwalker.useragentutils.OperatingSystem.IOS;
import static nl.bitwalker.useragentutils.OperatingSystem.MAC_OS_X_IPAD;
import static nl.bitwalker.useragentutils.OperatingSystem.UNKNOWN;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sourceforge.stripes.action.ActionBeanContext;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;

import com.studio4096.portfolio.cnstants.DefaultScreenSize;
import com.studio4096.portfolio.cnstants.Globals;
import com.studio4096.portfolio.dto.BookDto;
import com.studio4096.portfolio.dto.ClientInfoDto;
import com.studio4096.portfolio.dto.RectSize;
import com.studio4096.portfolio.entity.ImageData;
import com.studio4096.portfolio.entity.Site;

public class PortfolioActionBeanContext extends ActionBeanContext {
	Log log = new Log4JLogger(this.getClass().getName());

	public HttpSession getSession() {
		return getRequest().getSession();
	}

	public Map<Long, BookDto> getBookCache() {
		@SuppressWarnings("unchecked")
		Map<Long, BookDto> bookCache = getCache(HashMap.class,
				Globals.PAGE_CACHE);
		return bookCache;
	}

	public Map<Long, ImageData> getImageDataCache() {
		@SuppressWarnings("unchecked")
		Map<Long, ImageData> cache = getCache(HashMap.class,
				Globals.IMAGE_CACHE);
		return cache;
	}

	public Site getSiteCache() {
		Site cache = (Site) getServletContext()
				.getAttribute(Globals.SITE_CACHE);
		return cache;
	}

	public void setSiteCache(Site site) {
		getServletContext().setAttribute(Globals.SITE_CACHE, site);
	}

	public ClientInfoDto getClientInfo() {
		HttpSession session = getSession();
		ClientInfoDto clientInfo = (ClientInfoDto) session
				.getAttribute(Globals.CLIENT_INFO);

		if (clientInfo != null) {
			return clientInfo;
		}

		// clientInfo =DynaBeanFactory.create(ClientInfoDto.class);
		clientInfo = new ClientInfoDto();
		session.setAttribute(Globals.CLIENT_INFO, clientInfo);
		
		// User Agent
		{
			String userAgentString = getRequest().getHeader("User-Agent");
			if (userAgentString == null) {
				userAgentString = "";
			}
			UserAgent userAgent = UserAgent
					.parseUserAgentString(userAgentString);

			clientInfo.browser = userAgent.getBrowser();
			clientInfo.browserGroup = userAgent.getBrowser().getGroup();
			clientInfo.browserVersion = userAgent.getBrowserVersion();
			clientInfo.os = userAgent.getOperatingSystem();
		}
		
		// Screen size
		// TODO clientSizeは？
		{
			RectSize screenSize = new RectSize();
			clientInfo.setScreenSize(screenSize);
			DefaultScreenSize def;

			OperatingSystem os = clientInfo.os;
			if (UNKNOWN == os) {
				// default
				def = W1024H768;
			} else {
				switch (os.getDeviceType()) {
				case COMPUTER:
					// 1024x768
					def = W1024H768;
					break;
				case MOBILE:
					// iPhone size or iPod Touch
					def = (IOS == os.getGroup()) ? W480H320 : W480H320;
					break;
				case TABLET:
					// iPad size.
					def = (MAC_OS_X_IPAD == os) ? W1024H768 : W1024H768;
					break;
				case GAME_CONSOLE:
					def = W480H320;
					break;
				case DMR:
					def = W480H320;
					break;
				default:
					def = W1024H768;
					break;
				}
			}
			screenSize.w = def.W;
			screenSize.h = def.H;
		}
		return clientInfo;
	}

	@SuppressWarnings("unchecked")
	private <T> T getCache(Class<T> type, String key) {
		T cache = (T) getServletContext().getAttribute(key);
		if (cache == null) {
			try {
				cache = type.newInstance();
			} catch (InstantiationException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			getServletContext().setAttribute(key, cache);
		}
		return cache;

	}

}
