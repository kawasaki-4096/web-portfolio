package com.studio4096.portfolio.cnstants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationSetting {
	private static ApplicationSetting instance;
 	private ApplicationSetting(){super();}
	
	public static final Properties PROP;
	static {
		PROP=new Properties();
		try {
			InputStream is =ApplicationSetting.class.getResourceAsStream("/ApplicationSetting.properties");
			PROP.load(is);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public static ApplicationSetting getInstance(){
		if(instance ==null){
			instance =new ApplicationSetting();
		}
		return instance;
	}
	
	private String siteName;
	public String getSiteName(){
		if(siteName ==null){
			String value =PROP.getProperty("siteName");
			siteName = (value!=null) ? value : "studio 4096 ";
		}
		return siteName;
	}
	private String contentFileBasePath;
	public String getcontentFileBasePath(){
		if(contentFileBasePath ==null){
			String value =PROP.getProperty("contentFileBasePath");
			contentFileBasePath = (value!=null) ? value : "/var/httpd/www";
		}
		return contentFileBasePath;
	}
	private String imageDirName;
	public String getImageDirName() {
		if(imageDirName ==null){
			String value =PROP.getProperty("imageDirName");
			imageDirName = (value!=null) ? value : "/images/full";
		}
		return imageDirName;
	}
	private String thumbsDirName;
	public String getThumbsDirName() {
		if(thumbsDirName ==null){
			String value =PROP.getProperty("thumbsDirName");
			thumbsDirName = (value!=null) ? value : "/images/thumbs";
		}
		return thumbsDirName;
	}
	private String contactEMailAddress;
	public String getContactEMailAddress(){
		if(contactEMailAddress ==null){
			String value =PROP.getProperty("contactEMailAddress");
			contactEMailAddress = (value!=null) ? value : "contact@studio4096.com";
		}
		return contactEMailAddress;
	}
	private Integer minBookCounts;
	public int getMinBookCounts(){
		if(minBookCounts ==null){
			String value =PROP.getProperty("minBookCounts");
			minBookCounts = (value!=null && value.matches("^[0-9]+$")) ? Integer.valueOf(value) : 5;
		}
		return minBookCounts;
	}
	
	

}
