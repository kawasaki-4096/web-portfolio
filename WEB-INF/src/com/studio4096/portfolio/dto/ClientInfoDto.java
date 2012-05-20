package com.studio4096.portfolio.dto;

import java.io.Serializable;

import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.BrowserType;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.Version;

public class ClientInfoDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9160586883470013366L;
	public Browser getBrowser() {
		return browser;
	}
	public void setBrowser(Browser browser) {
		this.browser = browser;
	}
	public Browser getBrowserGroup() {
		return browserGroup;
	}
	public void setBrowserGroup(Browser browserGroup) {
		this.browserGroup = browserGroup;
	}
	public OperatingSystem getOs() {
		return os;
	}
	public void setOs(OperatingSystem os) {
		this.os = os;
	}
	public RectSize getScreenSize() {
		return screenSize;
	}
	public void setScreenSize(RectSize screenSize) {
		this.screenSize = screenSize;
	}
	public RectSize getClientSize() {
		return clientSize;
	}
	public void setClientSize(RectSize clientSize) {
		this.clientSize = clientSize;
	}
	public RectSize getClientSizeWithoutScrollBar() {
		return clientSizeWithoutScrollBar;
	}
	public void setClientSizeWithoutScrollBar(RectSize clientSizeWithoutScrollBar) {
		this.clientSizeWithoutScrollBar = clientSizeWithoutScrollBar;
	}
	public Browser browser;
	public Browser browserGroup;
	public Version browserVersion;
	public Version getBrowserVersion() {
		return browserVersion;
	}
	public void setBrowserVersion(Version browserVersion) {
		this.browserVersion = browserVersion;
	}
	public OperatingSystem os;
	public RectSize screenSize;
	public RectSize clientSize;
	public RectSize clientSizeWithoutScrollBar;
	public boolean sendedFlag;
	public boolean getSendedFlag() { return sendedFlag; }
	public void setSendedFlag(boolean sendedFlag) { this.sendedFlag = sendedFlag; }
	
}
