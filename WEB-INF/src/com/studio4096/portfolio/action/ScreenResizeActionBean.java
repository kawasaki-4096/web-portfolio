package com.studio4096.portfolio.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.arnx.jsonic.JSON;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.OperatingSystem;
import static nl.bitwalker.useragentutils.OperatingSystem.*;
import nl.bitwalker.useragentutils.UserAgent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.Log4JLogger;

import com.studio4096.portfolio.cnstants.DefaultScreenSize;
import static com.studio4096.portfolio.cnstants.DefaultScreenSize.*;
import com.studio4096.portfolio.cnstants.Globals;
import com.studio4096.portfolio.dto.ClientInfoDto;
import com.studio4096.portfolio.dto.RectSize;

public class ScreenResizeActionBean extends BaseActionBean {
	Log log = new Log4JLogger(this.getClass().getName());
	public RectSize sc;
	public RectSize v;//view size(表示領域のみ)
	public RectSize vA;// view all size(非表示領域含む)

	public ClientInfoDto screenInfo;

	public void f2() {
		RectSize contentSize, screenSize, clientSize, resized, scr;
		// TODO 絵のサイズを考慮するよう修正する。

		HttpSession session = getContext().getSession();
		clientInfo =getContext().getClientInfo();
		screenSize = clientInfo.getScreenSize();
		if (sc != null) {
			// if client screen size is sent.
			screenSize.w = sc.w;
			screenSize.h = sc.h;
		}
	}

	public void f() {
		HttpServletRequest req = getContext().getRequest();
		String userAgentString = req.getHeader("User-Agent");
		if (userAgentString == null) {
			// default ScreenSize
		}
		UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
		OperatingSystem os = userAgent.getOperatingSystem();
		if (OperatingSystem.UNKNOWN == os) {
			// default
		}
		switch (userAgent.getOperatingSystem().getDeviceType()) {
		case COMPUTER:
			// 1024x768
			break;
		case MOBILE:
			if (OperatingSystem.MAC_OS == os.getGroup()
					|| OperatingSystem.IOS == os.getGroup()) {
				// iPhone size.
				// or iPod Touch
			}
			break;
		case TABLET:
			if (OperatingSystem.MAC_OS_X_IPAD == os) {
				// iPad size.
			}
			break;
		case GAME_CONSOLE:

			break;
		case DMR:

			break;
		default:
			break;
		}

	}

	public static void main(String[] args) {
		UserAgent userAgent = UserAgent.parseUserAgentString(null);
		System.out.println(userAgent);
		String userAgentString = "";// null;//"hogehoge";
		userAgent = UserAgent.parseUserAgentString(userAgentString);
		System.out.println(userAgent);
		System.out.println(userAgent.getBrowser());
		System.out.println(userAgent.getOperatingSystem());
		System.out.println(userAgent.getBrowser() == Browser.UNKNOWN);
		System.out.println(userAgent.getBrowser().equals(Browser.UNKNOWN));

	}

	public Resolution resize() {

		// getUserAgent

		if (sc != null) {
			RectSize size = new RectSize();
			clientInfo.screenSize = size;
			size.w = sc.w;
			size.h = sc.h;
			clientInfo.sendedFlag=true;
		}
		if(clientInfo.clientSize == null){
			clientInfo.clientSize = new RectSize();
		}
		clientInfo.clientSize.w = v.w;
		clientInfo.clientSize.h = v.h;
		return new StreamingResolution("application/json; charset=utf-8",
				JSON.encode(clientInfo.clientSize));
	}
	/*
	 * public static class RectSize implements Serializable{ private static
	 * final long serialVersionUID = -490077723866543640L; public Integer w;
	 * public Integer h; }
	 */
}
